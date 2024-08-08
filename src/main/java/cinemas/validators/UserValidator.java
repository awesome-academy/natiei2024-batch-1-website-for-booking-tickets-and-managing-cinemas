package cinemas.validators;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;

public class UserValidator {
    @NotNull
    @Email
    private String email;
    @NotNull
    @Size(min=8)
    private String password;

    private boolean rememberMe;

    public UserValidator() {
    }

    public UserValidator(String email, String password) {
        this.email = email;
        this.password = password;
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

    public UserValidator setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
        return this;
    }
}
