package kz.reserve.backend.service;

import kz.reserve.backend.domain.Order;
import kz.reserve.backend.domain.OrderState;
import kz.reserve.backend.domain.User;
import kz.reserve.backend.payload.request.OrderStateChangeRequest;
import kz.reserve.backend.payload.request.OrderStateResponse;
import kz.reserve.backend.payload.response.MessageResponse;
import kz.reserve.backend.payload.response.OrderListResponse;
import kz.reserve.backend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ServiceUtils serviceUtils;

    public ResponseEntity<?> getOrders() {
        User user = serviceUtils.getPrincipal();
        List<Order> orders = orderRepository.findAllByRestaurant(user.getRestaurant());
        return ResponseEntity.ok(new OrderListResponse(orders));
    }

    public ResponseEntity<?> getOrderStates() {
        return ResponseEntity.ok(new OrderStateResponse(OrderState.values()));
    }

    public ResponseEntity<?> changeOrderState(Long orderId, OrderStateChangeRequest orderStateChangeRequest) {
        try {
            Order order = orderRepository.getOne(orderId);

            String message = String.format("Hello %s! \n" +
                    "Your order state has been changed from %s to %s.\n" +
                    "", order.getClient().getName(), order.getState().name(), orderStateChangeRequest.getOrderState().name());

            order.setState(orderStateChangeRequest.getOrderState());
            orderRepository.save(order);

            serviceUtils.sendMessageToUser(order.getClient(), "Order State", message);

            return ResponseEntity.ok().body(new MessageResponse("Success"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

}
