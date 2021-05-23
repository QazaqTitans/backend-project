package kz.reserve.backend.service;

import kz.reserve.backend.domain.*;
import kz.reserve.backend.payload.request.EmailRequest;
import kz.reserve.backend.payload.request.RestaurantRequest;
import kz.reserve.backend.payload.request.SearchRequest;
import kz.reserve.backend.payload.response.JSONArrayResponse;
import kz.reserve.backend.payload.response.MessageResponse;
import kz.reserve.backend.payload.response.RestaurantResponse;
import kz.reserve.backend.payload.response.RestaurantSearchResponse;
import kz.reserve.backend.repository.CommentRepository;
import kz.reserve.backend.repository.RestaurantRepository;
import kz.reserve.backend.repository.TableRepository;
import kz.reserve.backend.repository.UserRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ServiceUtils serviceUtils;

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private CommentRepository commentRepository;

    public ResponseEntity<?> getRestaurants() {
        List<Restaurant> restaurantList = restaurantRepository.findAll();
        return ResponseEntity.ok(new RestaurantResponse(restaurantList));
    }

    public ResponseEntity<?> addRestaurant(RestaurantRequest restaurantRequest, MultipartFile multipartFile) {
        try {
            String password = serviceUtils.generatePassword(8);

            Set<Role> roles = new HashSet<>();
            roles.add(Role.restaurantAdmin);

            User user = new User(restaurantRequest.getAdminName(), restaurantRequest.getAdminSurname(),
                    restaurantRequest.getAdminEmail(),  restaurantRequest.getAdminPhone(), passwordEncoder.encode(password), roles);
            userRepository.save(user);

            Restaurant restaurant = new Restaurant();

            restaurant.setName(restaurantRequest.getRestaurantName());
            restaurant.setAddress(restaurantRequest.getAddress());
            restaurant.setAdmin(user);
            restaurant.setDescription(restaurantRequest.getDescription());
            restaurant.setMapCoordination(restaurantRequest.getMapCoordination());
            restaurant.setImageSrc(serviceUtils.saveUploadedFile(multipartFile));
            restaurant.setMaxPrice(restaurantRequest.getMaxPrice());
            restaurant.setMinPrice(restaurantRequest.getMinPrice());

            restaurantRepository.save(restaurant);

            String message = String.format("Hello %s! \n" +
                    "Welcome to our site, you are admin of \"%s\" restaurant. \n" +
                    "Your login: %s\n" +
                    "Your password: %s\n", user.getName(), restaurant.getName(), user.getEmail(), password);

            serviceUtils.sendMessageToUser(user, "Registration", message);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }


        return ResponseEntity.ok(new MessageResponse("Success"));
    }

    public ResponseEntity<?> updateRestaurant(Long restaurantId, RestaurantRequest restaurantRequest) {
        try {
            Restaurant restaurant = restaurantRepository.getOne(restaurantId);

            restaurant.setName(restaurantRequest.getRestaurantName());
            restaurant.setMapCoordination(restaurantRequest.getMapCoordination());
            restaurant.setDescription(restaurantRequest.getDescription());
            restaurant.setAddress(restaurantRequest.getAddress());
            restaurant.setMaxPrice(restaurantRequest.getMaxPrice());
            restaurant.setMinPrice(restaurantRequest.getMinPrice());

            restaurantRepository.save(restaurant);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }

        return ResponseEntity.ok(new MessageResponse("Success"));
    }

    public ResponseEntity<?> deleteRestaurant(Long restaurantId) {
        try {
            restaurantRepository.deleteById(restaurantId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }

        return ResponseEntity.ok(new MessageResponse("Success"));
    }


    public ResponseEntity<?> updateUserOfRestaurant(Long restaurantId, EmailRequest emailRequest) {
        try {
            String password = serviceUtils.generatePassword(8);
            Optional<User> optionalUser = userRepository.findByEmail(emailRequest.getAdminEmail());
            User user = null;

            if (optionalUser.isPresent())
                user = optionalUser.get();
            else {
                Set<Role> roles = new HashSet<>();
                roles.add(Role.restaurantAdmin);

                user = new User(emailRequest.getAdminName(), emailRequest.getAdminSurname(),
                    emailRequest.getAdminEmail(), emailRequest.getAdminPhone(), passwordEncoder.encode(password), roles);
                userRepository.save(user);
            }

            Restaurant restaurant = restaurantRepository.getOne(restaurantId);

            restaurant.setAdmin(user);
            restaurantRepository.save(restaurant);

            String message = String.format("Hello %s! \n" +
                    "Welcome to our site, you are now admin of \"%s\" restaurant. \n" +
                    "Your login: %s\n" +
                    "Your password: %s\n", user.getName(), password, restaurant.getName(), user.getEmail());

            serviceUtils.sendMessageToUser(user, "Admin", message);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }

        return ResponseEntity.ok(new MessageResponse("Success"));
    }

    public ResponseEntity<?> searchRestaurants(SearchRequest searchRequest) {
        try {
            DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime startTime = LocalDateTime.parse(searchRequest.getStartTime(), dateTimeFormat);
            LocalDateTime endTime = LocalDateTime.parse(searchRequest.getEndTime(), dateTimeFormat);
            List<Restaurant> restaurants;

            if (searchRequest.getSortEnum() == SortEnum.RATING)
                restaurants = restaurantRepository.searchRestaurantsAverage(startTime, endTime,
                        searchRequest.getPerson(), searchRequest.getMinPrice(), searchRequest.getMaxPrice(),
                        searchRequest.getFilter(), searchRequest.getStars());
            else
                restaurants = restaurantRepository.searchRestaurants(startTime, endTime,
                        searchRequest.getPerson(), searchRequest.getMinPrice(), searchRequest.getMaxPrice(),
                        searchRequest.getFilter(), searchRequest.getStars());

            List<RestaurantSearchResponse> searchResponse = new ArrayList<>();

            for (Restaurant restaurant : restaurants) {
                RestaurantSearchResponse restaurantObject = new RestaurantSearchResponse();

                restaurantObject.setRestaurant(restaurant);

                LocalDateTime nearestHour = LocalDateTime.now().plusHours(1).truncatedTo(ChronoUnit.HOURS);
                LocalDateTime endHour = LocalDateTime.of(LocalDate.now(), LocalTime.of(22, 1));

                List<String> hours = new ArrayList<>();

                while (nearestHour.isBefore(endHour)) {
                    ReservedTable reservedTable = tableRepository.findEmptyTable(nearestHour, nearestHour.plusHours(1),
                            searchRequest.getPerson(), restaurant.getId());

                    if (reservedTable != null) {
                        hours.add((nearestHour.getHour() < 10 ? "0" : "") + nearestHour.getHour() + ":00");
                    }

                    nearestHour = nearestHour.plusHours(1);
                }

                restaurantObject.setHours(hours);

                Double star = commentRepository.averageByRestaurant(restaurant);
                restaurantObject.setStar(star);

                searchResponse.add(restaurantObject);
            }

            return ResponseEntity.ok(searchResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }
}
