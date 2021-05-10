package kz.reserve.backend.payload.request;

import javax.validation.constraints.NotBlank;

public class TableRequest {
    @NotBlank
    private String name;
    private String imageSrc;
    private Long restaurantId;
    private int reservePrice;
    private boolean isForChildren;
    private boolean isTapchan;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getReservePrice() {
        return reservePrice;
    }

    public void setReservePrice(int reservePrice) {
        this.reservePrice = reservePrice;
    }

    public boolean isForChildren() {
        return isForChildren;
    }

    public void setForChildren(boolean forChildren) {
        isForChildren = forChildren;
    }

    public boolean isTapchan() {
        return isTapchan;
    }

    public void setTapchan(boolean tapchan) {
        isTapchan = tapchan;
    }
}
