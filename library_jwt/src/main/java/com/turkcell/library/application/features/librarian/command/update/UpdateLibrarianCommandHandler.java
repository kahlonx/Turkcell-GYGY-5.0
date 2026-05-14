package com.turkcell.library.application.features.librarian.command.update;

import com.turkcell.library.core.mediator.cqrs.RequestHandler;
import org.springframework.stereotype.Component;
import com.turkcell.library.domain.entity.Librarian;
import com.turkcell.library.persistence.repository.LibrarianRepository;
import com.turkcell.library.exception.type.NotFoundException;

@Component
public class UpdateLibrarianCommandHandler implements RequestHandler<UpdateLibrarianCommand, UpdatedLibrarianResponse> {
    private final LibrarianRepository repository;

    public UpdateLibrarianCommandHandler(LibrarianRepository repository) {
        this.repository = repository;
    }

    @Override
    public UpdatedLibrarianResponse handle(UpdateLibrarianCommand request) {
        Integer id = request.getId();
        Librarian entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Librarian not found"));
        if (request.getFirstName() != null) {
            entity.setFirstName(request.getFirstName());
        }
        if (request.getLastName() != null) {
            entity.setLastName(request.getLastName());
        }
        entity = repository.save(entity);
        UpdatedLibrarianResponse response = new UpdatedLibrarianResponse();
        response.setId(entity.getId());
        response.setFirstName(entity.getFirstName());
        response.setLastName(entity.getLastName());

        response.setFirstName(entity.getFirstName());
        response.setLastName(entity.getLastName());

        return response;
    }
}
