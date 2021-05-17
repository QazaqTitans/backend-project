package kz.reserve.backend.payload.request;

import kz.reserve.backend.domain.Position;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public interface IOrderRequest {
    Integer getPersonCount();
    String getStartTime();
    String getEndTime();
    Position getPosition();
    Boolean getChildren();
    Long getRestaurantId();
    String getFeatures();
}
