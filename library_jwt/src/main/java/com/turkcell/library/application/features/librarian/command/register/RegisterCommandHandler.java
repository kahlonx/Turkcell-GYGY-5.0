package com.turkcell.library.application.features.librarian.command.register;

import com.turkcell.library.core.mediator.cqrs.RequestHandler;
import com.turkcell.library.domain.entity.Librarian;
import com.turkcell.library.exception.type.UserAlreadyExistsException;
import com.turkcell.library.persistence.repository.LibrarianRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class RegisterCommandHandler implements RequestHandler<RegisterCommand, RegisterResponse> {

    private final LibrarianRepository librarianRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterCommandHandler(LibrarianRepository librarianRepository, PasswordEncoder passwordEncoder) {
        this.librarianRepository = librarianRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public RegisterResponse handle(RegisterCommand request) {
        if (librarianRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("A librarian with email '" + request.getEmail() + "' already exists.");
        }

        Librarian librarian = new Librarian();
        librarian.setFirstName(request.getFirstName());
        librarian.setLastName(request.getLastName());
        librarian.setEmail(request.getEmail());
        librarian.setPassword(passwordEncoder.encode(request.getPassword()));
        librarian.setRoles(request.getRoles() != null ? request.getRoles() : new ArrayList<>());
        librarian = librarianRepository.save(librarian);

        RegisterResponse response = new RegisterResponse();
        response.setId(librarian.getId());
        response.setEmail(librarian.getEmail());
        return response;
    }
}
