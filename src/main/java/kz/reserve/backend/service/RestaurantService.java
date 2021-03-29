package kz.reserve.backend.service;

import kz.reserve.backend.domain.Restaurant;
import kz.reserve.backend.payload.request.RestaurantRequest;
import kz.reserve.backend.payload.response.MessageResponse;
import kz.reserve.backend.payload.response.RestaurantResponse;
import kz.reserve.backend.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;


    public ResponseEntity<?> getRestaurants() {
        List<Restaurant> restaurantList = restaurantRepository.findAll();
        return ResponseEntity.ok(new RestaurantResponse(restaurantList));
    }
    public ResponseEntity<?> addRestaurant(RestaurantRequest restaurantRequest) {
        try {
            Restaurant restaurant = new Restaurant();

            restaurant.setName(restaurantRequest.getName());

            restaurant.setAdmin_id(restaurantRequest.getAdmin_id());
            restaurant.setMap_coordination(restaurantRequest.getMap_coordination());
            restaurantRepository.save(restaurant);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }

        return ResponseEntity.ok(new MessageResponse("Success"));
    }

    public ResponseEntity<?> updateRestaurant(Long RestaurantId, RestaurantRequest restaurantRequest) {
        try {
            Restaurant restaurant = restaurantRepository.getOne(RestaurantId);

            restaurant.setName(restaurantRequest.getName());
            restaurant.setAdmin_id(restaurantRequest.getAdmin_id());
            restaurant.setMap_coordination(restaurantRequest.getMap_coordination());
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


}
