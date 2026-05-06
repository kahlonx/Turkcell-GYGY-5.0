package com.turkcell.library.application.features.librarian.command.create;

import com.turkcell.library.core.mediator.cqrs.RequestHandler;
import org.springframework.stereotype.Component;
import com.turkcell.library.domain.entity.Librarian;
import com.turkcell.library.persistence.repository.LibrarianRepository;

@Component
public class CreateLibrarianCommandHandler implements RequestHandler<CreateLibrarianCommand, CreatedLibrarianResponse> {
    private final LibrarianRepository repository;

    public CreateLibrarianCommandHandler(LibrarianRepository repository) {
        this.repository = repository;
    }

    @Override
    public CreatedLibrarianResponse handle(CreateLibrarianCommand request) {
        Librarian entity = new Librarian();
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity = repository.save(entity);
        CreatedLibrarianResponse response = new CreatedLibrarianResponse();
        response.setId(entity.getId());
        response.setFirstName(entity.getFirstName());
        response.setLastName(entity.getLastName());
        return response;
    }
}
