package kz.reserve.backend.payload.response;

import kz.reserve.backend.domain.Restaurant;

import java.util.List;

public class RestaurantSearchResponse {
    private Restaurant restaurant;

    private List<String> hours;

    private Double star;

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<String> getHours() {
        return hours;
    }

    public void setHours(List<String> hours) {
        this.hours = hours;
    }

    public Double getStar() {
        return star;
    }

    public void setStar(Double star) {
        this.star = star;
    }
}
