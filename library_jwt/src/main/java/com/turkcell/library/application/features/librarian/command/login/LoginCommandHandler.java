package com.turkcell.library.application.features.librarian.command.login;

import com.turkcell.library.core.mediator.cqrs.RequestHandler;
import com.turkcell.library.core.security.jwt.JwtService;
import com.turkcell.library.domain.entity.Librarian;
import com.turkcell.library.exception.type.InvalidCredentialsException;
import com.turkcell.library.persistence.repository.LibrarianRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class LoginCommandHandler implements RequestHandler<LoginCommand, LoginResponse> {

    private final LibrarianRepository librarianRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public LoginCommandHandler(LibrarianRepository librarianRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.librarianRepository = librarianRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public LoginResponse handle(LoginCommand request) {
        Librarian librarian = librarianRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password."));

        if (!passwordEncoder.matches(request.getPassword(), librarian.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password.");
        }

        String token = jwtService.generateToken(librarian.getEmail(), librarian.getRoles());
        return new LoginResponse(token);
    }
}
