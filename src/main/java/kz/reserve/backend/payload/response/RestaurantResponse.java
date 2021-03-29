package kz.reserve.backend.payload.response;

import kz.reserve.backend.domain.Restaurant;

import java.util.List;

public class RestaurantResponse {
    private List<Restaurant> restaurantList;

    public RestaurantResponse(List<Restaurant> categoryList) {
        this.restaurantList = restaurantList;
    }

    public List<Restaurant> getRestaurantList() {
        return restaurantList;
    }

    public void setRestaurantList(List<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
    }

}
