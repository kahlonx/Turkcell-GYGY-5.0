package com.turkcell.library.application.features.librarian.command.delete;

import com.turkcell.library.core.mediator.cqrs.RequestHandler;
import org.springframework.stereotype.Component;
import com.turkcell.library.persistence.repository.LibrarianRepository;

@Component
public class DeleteLibrarianCommandHandler implements RequestHandler<DeleteLibrarianCommand, Void> {
    private final LibrarianRepository repository;

    public DeleteLibrarianCommandHandler(LibrarianRepository repository) {
        this.repository = repository;
    }

    @Override
    public Void handle(DeleteLibrarianCommand request) {
        Integer id = request.getId();
        repository.deleteById(id);
        return null;
    }
}
