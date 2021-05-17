package kz.reserve.backend.repository;

import kz.reserve.backend.domain.Discount;
import kz.reserve.backend.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiscountRepository extends JpaRepository<Discount, Long> {
    List<Discount> findByRestaurant(Restaurant restaurant);
}
