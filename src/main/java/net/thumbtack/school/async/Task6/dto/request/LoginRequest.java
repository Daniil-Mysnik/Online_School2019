package net.thumbtack.school.async.Task6.dto.request;

import net.thumbtack.school.async.Task6.validator.MaxLength;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class LoginRequest {
//    @NotBlank(message = "Login can't be empty")
//    @NotNull(message = "Login can't be null")
//    @MaxLength(message = "Login is too long")
//    @Pattern(regexp = "[a-zA-Zа-яА-Я0-9]+", message = "Login must contains only Russian and English letters and numbers")
    private String login;

//    @NotBlank(message = "Password can't be empty")
//    @NotNull(message = "Password can't be null")
////    @PasswordLength(message = "Password is too short")
//    @MaxLength(message = "Password is too long")
    private String password;

    public LoginRequest() {
    }

    public LoginRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
