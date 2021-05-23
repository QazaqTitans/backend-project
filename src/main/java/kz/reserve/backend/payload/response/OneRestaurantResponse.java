package kz.reserve.backend.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import kz.reserve.backend.domain.Restaurant;

import java.util.List;

public class OneRestaurantResponse {
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private Restaurant restaurantList;

    public OneRestaurantResponse(Restaurant restaurantList) {
        this.restaurantList = restaurantList;
    }

    public Restaurant getRestaurantList() {
        return restaurantList;
    }

    public void setRestaurantList(Restaurant restaurantList) {
        this.restaurantList = restaurantList;
    }
}
