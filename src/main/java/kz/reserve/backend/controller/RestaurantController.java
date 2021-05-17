package kz.reserve.backend.controller;


import kz.reserve.backend.payload.request.CategoryRequest;
import kz.reserve.backend.payload.request.EmailRequest;
import kz.reserve.backend.payload.request.RestaurantRequest;
import kz.reserve.backend.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping()
    public ResponseEntity<?> getRestaurants() {
        return restaurantService.getRestaurants();
    }

    @PreAuthorize("hasAuthority('superAdmin')")
    @PostMapping()
    public ResponseEntity<?> addCategory(@Valid @ModelAttribute RestaurantRequest restaurantRequest, @RequestPart MultipartFile multipartFile) {
        return restaurantService.addRestaurant(restaurantRequest, multipartFile);
    }

    @PreAuthorize("hasAuthority('superAdmin')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRestaurant(@Valid @Min(1) @PathVariable Long id, @Valid @ModelAttribute RestaurantRequest restaurantRequest) {
        return restaurantService.updateRestaurant(id, restaurantRequest);
    }

    @PreAuthorize("hasAuthority('superAdmin')")
    @PutMapping("/update-admin/{id}")
    public ResponseEntity<?> updateRestaurant(@Valid @Min(1) @PathVariable Long id, @Valid @ModelAttribute EmailRequest emailRequest) {
        return restaurantService.updateUserOfRestaurant(id, emailRequest);
    }

    @PreAuthorize("hasAuthority('superAdmin')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRestaurant(@Valid @Min(1) @PathVariable Long id) {
        return restaurantService.deleteRestaurant(id);
    }
}
