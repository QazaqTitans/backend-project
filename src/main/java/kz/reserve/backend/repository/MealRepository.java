package kz.reserve.backend.repository;

import kz.reserve.backend.domain.Meal;
import kz.reserve.backend.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MealRepository  extends JpaRepository<Meal, Long> {

    List<Meal> findByRestaurant(Restaurant restaurant);
}
