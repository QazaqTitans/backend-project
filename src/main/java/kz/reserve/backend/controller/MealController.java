package kz.reserve.backend.controller;


import kz.reserve.backend.domain.User;
import kz.reserve.backend.payload.request.MealRequest;
import kz.reserve.backend.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@EnableAutoConfiguration
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/meal")
@PreAuthorize("hasAuthority('restaurantAdmin')")
public class MealController {
    @Autowired
    private MealService mealService;


    @GetMapping()
    public ResponseEntity<?> getMeals() {
        return mealService.getMeals();
    }

    @PostMapping()
    public ResponseEntity<?> addMeal(@Valid @RequestBody MealRequest mealrequest) {
        return mealService.addMeal(mealrequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMeal(@Valid @Min(1) @PathVariable Long id, @Valid @RequestBody MealRequest mealRequest) {
        return mealService.updateMeal(id, mealRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMeal(@Valid @Min(1) @PathVariable Long id) {
        return mealService.deleteMeal(id);
    }

}
