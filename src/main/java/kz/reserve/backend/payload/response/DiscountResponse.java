package kz.reserve.backend.payload.response;

import kz.reserve.backend.domain.Discount;


import java.util.List;

public class DiscountResponse {
    private List<Discount> discountList;
    public DiscountResponse(List<Discount> discountList){
        this.discountList=discountList;
    }
    public List<Discount> getDiscountList() {
        return discountList;
    }

    public void setDiscountList(List<Discount> discountList) {
        this.discountList = discountList;
    }
}
