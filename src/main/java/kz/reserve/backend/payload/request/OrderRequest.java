package kz.reserve.backend.payload.request;

import kz.reserve.backend.domain.Position;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class OrderRequest implements IOrderRequest {
    @NotNull
    private Integer personCount;

    @NotNull
    private LocalDateTime startTime;

    @NotNull
    private LocalDateTime endTime;

    private Position position;

    @NotNull
    private Boolean isChildren;

    @NotNull
    private Long restaurantId;

    private String features;

    @NotBlank
    private String phone;

    @NotBlank
    private String email;

    @NotBlank
    private String surname;

    @NotBlank
    private String name;


    @Override
    public Integer getPersonCount() {
        return personCount;
    }

    public void setPersonCount(Integer personCount) {
        this.personCount = personCount;
    }

    @Override
    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    @Override
    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
