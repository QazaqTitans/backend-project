package kz.reserve.backend.payload.request;

import javax.validation.constraints.NotBlank;

public class TableRequest {
    @NotBlank
    private String name;
    private String image_src;
    private Long restaurant_id;
    private int reserve_price;
    private boolean for_children;
    private boolean tapchan;


    public boolean isTapchan() {
        return tapchan;
    }

    public void setTapchan(boolean tapchan) {
        this.tapchan = tapchan;
    }

    public boolean isFor_children() {
        return for_children;
    }

    public void setFor_children(boolean for_children) {
        this.for_children = for_children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage_src() {
        return image_src;
    }

    public void setImage_src(String image_src) {
        this.image_src = image_src;
    }

    public Long getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(Long restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public int getReserve_price() {
        return reserve_price;
    }

    public void setReserve_price(int reserve_price) {
        this.reserve_price = reserve_price;
    }
}
