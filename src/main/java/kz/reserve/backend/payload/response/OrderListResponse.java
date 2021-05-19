package kz.reserve.backend.payload.response;

import kz.reserve.backend.domain.Order;

import java.util.List;

public class OrderListResponse {

    private List<Order> orders;

    public OrderListResponse(List<Order> orders) {
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
