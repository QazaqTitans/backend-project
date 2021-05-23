package kz.reserve.backend.payload.request;

import kz.reserve.backend.domain.OrderState;

public class OrderStateResponse {

    private OrderState[] orderState;

    public OrderStateResponse(OrderState[] orderState) {
        this.orderState = orderState;
    }

    public OrderState[] getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState[] orderState) {
        this.orderState = orderState;
    }
}
