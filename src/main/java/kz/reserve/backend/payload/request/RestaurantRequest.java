package kz.reserve.backend.payload.request;

import kz.reserve.backend.domain.Restaurant;

import javax.validation.constraints.NotBlank;

public class RestaurantRequest {
    @NotBlank
    private String name;
    private int admin_id;
    private Long map_coordination;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public Long getMap_coordination() {
        return map_coordination;
    }

    public void setMap_coordination(Long map_coordination) {
        this.map_coordination = map_coordination;
    }
}
