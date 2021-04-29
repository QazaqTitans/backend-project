package kz.reserve.backend.service;

import kz.reserve.backend.domain.Meal;
import kz.reserve.backend.payload.request.MealRequest;
import kz.reserve.backend.payload.response.MealResponse;
import kz.reserve.backend.payload.response.MessageResponse;
import kz.reserve.backend.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MealService {
    @Autowired
    private MealRepository mealRepository;

    public ResponseEntity<?> getMeals() {
        List<Meal> mealList = mealRepository.findAll();
        return ResponseEntity.ok(new MealResponse(mealList));
    }

    public ResponseEntity<?> addMeal(MealRequest mealRequest) {
        try {
            Meal meal = new Meal();

            meal.setName(mealRequest.getName());
            meal.setDescription(mealRequest.getDescription());
            meal.setFeatures(mealRequest.getFeatures());
            meal.setIs_finished(mealRequest.isIs_finished());
            meal.setPrice(mealRequest.getPrice());
            meal.setStart_time(mealRequest.getStart_time());
            meal.setEnd_time(mealRequest.getEnd_time());


            mealRepository.save(meal);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }

        return ResponseEntity.ok(new MessageResponse("Success"));
    }

    public ResponseEntity<?> updateMeal(Long mealID, MealRequest mealRequest) {
        try {
            Meal meal = mealRepository.getOne(mealID);

            meal.setName(mealRequest.getName());
            meal.setDescription(mealRequest.getDescription());
            meal.setFeatures(mealRequest.getFeatures());
            meal.setIs_finished(mealRequest.isIs_finished());
            meal.setPrice(mealRequest.getPrice());
            meal.setStart_time(mealRequest.getStart_time());
            meal.setEnd_time(mealRequest.getEnd_time());


            mealRepository.save(meal);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }

        return ResponseEntity.ok(new MessageResponse("Success"));
    }

    public ResponseEntity<?> deleteMeal(Long mealId) {
        try {
            mealRepository.deleteById(mealId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }

        return ResponseEntity.ok(new MessageResponse("Success"));
    }
}
