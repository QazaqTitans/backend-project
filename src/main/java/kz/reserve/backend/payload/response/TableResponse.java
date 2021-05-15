package kz.reserve.backend.payload.response;

import kz.reserve.backend.domain.ReservedTable;

import java.util.List;

public class TableResponse {
    private List<ReservedTable> reservedTableList;
    public TableResponse(List<ReservedTable> reservedTableList){
        this.reservedTableList = reservedTableList;
    }
    public List<ReservedTable> getTableList() {
        return reservedTableList;
    }

    public void setTableList(List<ReservedTable> reservedTableList) {
        this.reservedTableList = reservedTableList;
    }
}
