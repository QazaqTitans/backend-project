package kz.reserve.backend.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import kz.reserve.backend.domain.Order;
import kz.reserve.backend.domain.ReservedTable;

public class OrderResponse {
    private ReservedTable reservedTable;

    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private Order order;

    public OrderResponse(ReservedTable reservedTable, Order order) {
        this.reservedTable = reservedTable;
        this.order = order;
    }

    public ReservedTable getReservedTable() {
        return reservedTable;
    }

    public void setReservedTable(ReservedTable reservedTable) {
        this.reservedTable = reservedTable;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
