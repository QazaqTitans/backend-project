package kz.reserve.backend.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class OrderMealRequest {

    private List<Long> mealIds;

    public List<Long> getMealIds() {
        return mealIds;
    }

    public void setMealIds(List<Long> mealIds) {
        this.mealIds = mealIds;
    }
}
