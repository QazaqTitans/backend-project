package kz.reserve.backend.payload.request;

import kz.reserve.backend.domain.Position;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

public class TableRequest {
    @NotBlank
    private String name;
    private String imageSrc;
    private Integer reservePrice;

    private Position position;

    private Boolean isForChildren;
    private Integer personCount;
    private Long restaurantId;

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

    public Integer getReservePrice() {
        return reservePrice;
    }

    public void setReservePrice(Integer reservePrice) {
        this.reservePrice = reservePrice;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Boolean getForChildren() {
        return isForChildren;
    }

    public void setForChildren(Boolean forChildren) {
        isForChildren = forChildren;
    }

    public Integer getPersonCount() {
        return personCount;
    }

    public void setPersonCount(Integer personCount) {
        this.personCount = personCount;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }
}
