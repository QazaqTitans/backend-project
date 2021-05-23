package kz.reserve.backend.payload.request;

import kz.reserve.backend.domain.OrderState;
import kz.reserve.backend.domain.Position;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class OrderStateChangeRequest {
    private OrderState orderState;

    public OrderStateChangeRequest(OrderState orderState) {
        this.orderState = orderState;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }
}
