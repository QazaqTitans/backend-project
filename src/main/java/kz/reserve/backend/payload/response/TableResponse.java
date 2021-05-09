package kz.reserve.backend.payload.response;

import kz.reserve.backend.domain.Table;

import java.util.List;

public class TableResponse {
    private List<Table> tableList;
    public TableResponse(List<Table> tableList){
        this.tableList=tableList;
    }
    public List<Table> getTableList() {
        return tableList;
    }

    public void setTableList(List<Table> tableList) {
        this.tableList = tableList;
    }
}
