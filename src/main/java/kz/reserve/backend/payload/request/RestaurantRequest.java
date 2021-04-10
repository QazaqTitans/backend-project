package kz.reserve.backend.payload.request;

import kz.reserve.backend.domain.Restaurant;

import javax.validation.constraints.NotBlank;

public class RestaurantRequest {
    @NotBlank
    private String name;
    private Long adminId;
    private String mapCoordination;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getMapCoordination() {
        return mapCoordination;
    }

    public void setMapCoordination(String mapCoordination) {
        this.mapCoordination = mapCoordination;
    }
}
