package org.example.tapville.models.dtos;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserDto {
    @Size(min = 2, max = 20, message = "Username should be between 2 and 20 symbols.")
    private String username;
    @Size(min = 8, message = "Password should be at least 8 symbols.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*\\W)(?!.* ).{8,}$",
            message = "Password must contain one digit from 1 to 9, " +
                    "one lowercase letter, " +
                    "one uppercase letter, " +
                    "one special character, ")
    private String password;

    private String passwordConfirm;

    public UserDto() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
}
