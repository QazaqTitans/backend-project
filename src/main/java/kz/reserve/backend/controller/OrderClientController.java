package kz.reserve.backend.controller;

import kz.reserve.backend.payload.request.OrderRequest;
import kz.reserve.backend.payload.request.OrderUserRequest;
import kz.reserve.backend.service.OrderClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/api/client/order")
public class OrderClientController {

    @Autowired
    private OrderClientService orderClientService;

//    @GetMapping()
//    public ResponseEntity<?> getMyOrders() {
//        return null;
//    }
//
    @PostMapping("/make-order-not-registered")
    public ResponseEntity<?> makeOrder(@Valid @RequestBody OrderRequest orderRequest) {
        return orderClientService.makeOrderNewUser(orderRequest);
    }

    @PostMapping("/make-order")
    public ResponseEntity<?> makeOrderByUser(@Valid @RequestBody OrderUserRequest orderRequest) {
        return orderClientService.makeOrderExistUser(orderRequest);
    }

//    @PostMapping
//    public ResponseEntity<?> updateOrder() {
//        return null;
//    }
//
//    @PostMapping
//    public ResponseEntity<?> cancelOrder() {
//        return null;
//    }
}