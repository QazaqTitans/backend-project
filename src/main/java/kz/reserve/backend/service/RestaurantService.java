package kz.reserve.backend.service;

import kz.reserve.backend.domain.Restaurant;
import kz.reserve.backend.domain.Role;
import kz.reserve.backend.domain.User;
import kz.reserve.backend.payload.request.EmailRequest;
import kz.reserve.backend.payload.request.RestaurantRequest;
import kz.reserve.backend.payload.response.MessageResponse;
import kz.reserve.backend.payload.response.RestaurantResponse;
import kz.reserve.backend.repository.RestaurantRepository;
import kz.reserve.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    public ResponseEntity<?> getRestaurants() {
        List<Restaurant> restaurantList = restaurantRepository.findAll();
        return ResponseEntity.ok(new RestaurantResponse(restaurantList));
    }

    public ResponseEntity<?> addRestaurant(RestaurantRequest restaurantRequest) {
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
                    "Your login: %s\n", user.getName(), restaurant.getName(), user.getEmail());

            serviceUtils.sendMessageToUser(user, "Admin", message);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }

        return ResponseEntity.ok(new MessageResponse("Success"));
    }
}
