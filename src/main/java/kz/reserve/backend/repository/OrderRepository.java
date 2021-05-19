package kz.reserve.backend.repository;

import kz.reserve.backend.domain.Order;
import kz.reserve.backend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByClient(User client);
}
