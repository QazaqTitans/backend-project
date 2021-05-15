package kz.reserve.backend.repository;

import kz.reserve.backend.domain.Comment;
import kz.reserve.backend.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByRestaurant(Restaurant restaurant);
}
