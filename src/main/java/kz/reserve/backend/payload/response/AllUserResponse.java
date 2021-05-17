package kz.reserve.backend.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import kz.reserve.backend.domain.User;

import java.util.List;

public class AllUserResponse {
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private List<User> user;

    public AllUserResponse(List<User> user) {
        this.user = user;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }
}