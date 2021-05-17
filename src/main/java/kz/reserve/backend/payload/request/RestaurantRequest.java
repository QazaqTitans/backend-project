package kz.reserve.backend.payload.request;

import kz.reserve.backend.domain.Restaurant;

import javax.validation.constraints.NotBlank;

public class RestaurantRequest {
    @NotBlank
    private String restaurantName;

    private String adminName;

    private String adminSurname;

    private String adminPhone;

    private String adminEmail;

    @NotBlank
    private String mapCoordination;

    @NotBlank
    private String description;

    @NotBlank
    private String address;

    private Integer minPrice;

    private Integer maxPrice;

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminSurname() {
        return adminSurname;
    }

    public void setAdminSurname(String adminSurname) {
        this.adminSurname = adminSurname;
    }

    public String getAdminPhone() {
        return adminPhone;
    }

    public void setAdminPhone(String adminPhone) {
        this.adminPhone = adminPhone;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getMapCoordination() {
        return mapCoordination;
    }

    public void setMapCoordination(String mapCoordination) {
        this.mapCoordination = mapCoordination;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
    }
}
