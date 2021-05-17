package kz.reserve.backend.payload.request;

import javax.validation.constraints.NotBlank;

public class ResetRequest {
    @NotBlank
    private String email;

    public ResetRequest(@NotBlank String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}