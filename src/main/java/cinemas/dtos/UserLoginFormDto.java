package cinemas.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;

public class UserLoginFormDto {
    @NotNull
    @Email
    private String email;
    @NotNull
    @Size(min=8)
    private String password;

    private boolean rememberMe;

    public UserLoginFormDto() {
    }

    public UserLoginFormDto(String email, String password, boolean rememberMe) {
        this.email = email;
        this.password = password;
        this.rememberMe = rememberMe;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public UserLoginFormDto setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
        return this;
    }
}
