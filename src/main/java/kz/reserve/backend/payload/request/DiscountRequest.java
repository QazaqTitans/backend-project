package kz.reserve.backend.payload.request;
import javax.validation.constraints.NotBlank;
public class DiscountRequest {
    @NotBlank
    private String description;
    private Long restaurant_id;
    private double percentage;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(Long restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
}
