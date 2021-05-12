package kz.reserve.backend.repository;

import kz.reserve.backend.domain.ReservedTable;
import kz.reserve.backend.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TableRepository extends JpaRepository<ReservedTable, Long> {

    List<ReservedTable> findAllByRestaurant(Restaurant restaurant);
}
