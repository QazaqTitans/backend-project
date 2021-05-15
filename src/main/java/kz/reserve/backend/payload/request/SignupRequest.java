package kz.reserve.backend.payload.request;

import java.util.Set;

import javax.validation.constraints.*;

public class SignupRequest {

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

//    @NotBlank
//    @Size(min = 6, max = 40)
//    private String password;

    @NotBlank
    @Size(max = 30)
    private String name;

    @NotBlank
    @Size(max = 30)
    private String surname;

    @NotBlank
    private String phone;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}