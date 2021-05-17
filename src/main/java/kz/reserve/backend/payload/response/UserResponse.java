package kz.reserve.backend.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import kz.reserve.backend.domain.Category;
import kz.reserve.backend.domain.User;

import java.util.List;

public class UserResponse {
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private User user;

    public UserResponse(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}