package kz.reserve.backend.payload.response;

import kz.reserve.backend.domain.ReservedTable;

public class OrderResponse {
    private ReservedTable reservedTable;

    public OrderResponse(ReservedTable reservedTable) {
        this.reservedTable = reservedTable;
    }

    public ReservedTable getReservedTable() {
        return reservedTable;
    }

    public void setReservedTable(ReservedTable reservedTable) {
        this.reservedTable = reservedTable;
    }
}
