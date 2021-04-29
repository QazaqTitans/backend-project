package kz.reserve.backend.repository;

import kz.reserve.backend.domain.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository  extends JpaRepository<Meal, Long> {
}
