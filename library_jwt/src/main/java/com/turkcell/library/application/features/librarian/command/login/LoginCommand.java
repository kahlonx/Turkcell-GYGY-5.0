package com.turkcell.library.application.features.librarian.command.login;

import com.turkcell.library.core.mediator.cqrs.Request;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginCommand implements Request<LoginResponse> {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    public LoginCommand() {}

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
