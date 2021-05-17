package kz.reserve.backend.service;

import kz.reserve.backend.domain.Category;
import kz.reserve.backend.domain.Meal;
import kz.reserve.backend.domain.Restaurant;
import kz.reserve.backend.domain.User;
import kz.reserve.backend.payload.request.MealRequest;
import kz.reserve.backend.payload.response.MealResponse;
import kz.reserve.backend.payload.response.MessageResponse;
import kz.reserve.backend.repository.CategoryRepository;
import kz.reserve.backend.repository.MealRepository;
import kz.reserve.backend.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
@Service
public class MealService {
    @Autowired
    private MealRepository mealRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private ServiceUtils serviceUtils;

    public ResponseEntity<?> getMeals() {
        User user = serviceUtils.getPrincipal();
        List<Meal> mealList = mealRepository.findByRestaurant(user.getRestaurant());
        return ResponseEntity.ok(new MealResponse(mealList));
    }

    public ResponseEntity<?> getMeals(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.getOne(restaurantId);
        List<Meal> mealList = mealRepository.findByRestaurant(restaurant);
        return ResponseEntity.ok(new MealResponse(mealList));
    }

    public ResponseEntity<?> addMeal(MealRequest mealRequest, MultipartFile file) {
        try {
            Meal meal = new Meal();
            User user = serviceUtils.getPrincipal();

            meal.setRestaurant(user.getRestaurant());
            meal.setImageSrc(serviceUtils.saveUploadedFile(file));
            mealCreator(mealRequest, meal);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }

        return ResponseEntity.ok(new MessageResponse("Success"));
    }

    public ResponseEntity<?> updateMeal(Long mealID, MealRequest mealRequest) {
        try {
            Meal meal = mealRepository.getOne(mealID);
            mealCreator(mealRequest, meal);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }

        return ResponseEntity.ok(new MessageResponse("Success"));
    }

    private void mealCreator(MealRequest mealRequest, Meal meal) throws IOException {
        Category category = categoryRepository.getOne(mealRequest.getCategoryId());

        meal.setCategory(category);
        meal.setName(mealRequest.getName());
        meal.setDescription(mealRequest.getDescription());
        meal.setFeatures(mealRequest.getFeatures());
        meal.setFinished(mealRequest.isFinished());
        meal.setPrice(mealRequest.getPrice());
        meal.setTime(mealRequest.getTime());

        mealRepository.save(meal);
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
