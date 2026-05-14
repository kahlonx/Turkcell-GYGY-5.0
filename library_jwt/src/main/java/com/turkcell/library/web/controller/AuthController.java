package com.turkcell.library.web.controller;

import com.turkcell.library.application.features.librarian.command.login.LoginCommand;
import com.turkcell.library.application.features.librarian.command.login.LoginResponse;
import com.turkcell.library.application.features.librarian.command.register.RegisterCommand;
import com.turkcell.library.application.features.librarian.command.register.RegisterResponse;
import com.turkcell.library.core.mediator.Mediator;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final Mediator mediator;

    public AuthController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterResponse register(@Valid @RequestBody RegisterCommand command) {
        return mediator.send(command);
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginCommand command) {
        return mediator.send(command);
    }
}
