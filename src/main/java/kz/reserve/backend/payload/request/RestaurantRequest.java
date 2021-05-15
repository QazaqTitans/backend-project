package kz.reserve.backend.payload.request;

import kz.reserve.backend.domain.Restaurant;

import javax.validation.constraints.NotBlank;

public class RestaurantRequest {
    @NotBlank
    private String name;

    private String email;

    @NotBlank
    private String mapCoordination;

    @NotBlank
    private String description;

    @NotBlank
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
