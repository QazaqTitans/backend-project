package kz.reserve.backend.payload.request;

import kz.reserve.backend.domain.Position;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class OrderUserRequest implements IOrderRequest {
    @NotNull
    private Integer personCount;

    @NotNull
    private String startTime;

    @NotNull
    private String endTime;

    private Position position;

    @NotNull
    private Boolean isChildren;

    @NotNull
    private Long restaurantId;

    @NotBlank
    private String features;

    @Override
    public Integer getPersonCount() {
        return personCount;
    }

    public void setPersonCount(Integer personCount) {
        this.personCount = personCount;
    }

    @Override
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Override
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public Boolean getChildren() {
        return isChildren;
    }

    public void setChildren(Boolean children) {
        isChildren = children;
    }

    @Override
    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }
}
