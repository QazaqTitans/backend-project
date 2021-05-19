package kz.reserve.backend.controller;

import kz.reserve.backend.payload.request.OrderStateChangeRequest;
import kz.reserve.backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@CrossOrigin("*")
@Controller
@RequestMapping("/api/restaurant/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<?> getOrders() {
        return orderService.getOrders();
    }

    @GetMapping("/state")
    public ResponseEntity<?> getOrderState() {
        return orderService.getOrderStates();
    }

    @PostMapping("/{id}/change")
    public ResponseEntity<?> changeOrderState(@Valid @Min(1) @PathVariable Long id,
                                              @ModelAttribute OrderStateChangeRequest orderStateChangeRequest) {
        return orderService.changeOrderState(id, orderStateChangeRequest);
    }
}
