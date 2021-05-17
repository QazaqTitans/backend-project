package kz.reserve.backend.repository;

import kz.reserve.backend.domain.Comment;
import kz.reserve.backend.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByRestaurant(Restaurant restaurant);

    @Query(value = "select avg(star) from comment where restaurant_id = ?1", nativeQuery = true)
    Double averageByRestaurant(Restaurant restaurant);
}
