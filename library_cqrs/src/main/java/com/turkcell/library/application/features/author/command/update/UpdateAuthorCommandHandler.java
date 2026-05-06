package com.turkcell.library.application.features.author.command.update;

import com.turkcell.library.core.mediator.cqrs.RequestHandler;
import org.springframework.stereotype.Component;
import com.turkcell.library.domain.entity.Author;
import com.turkcell.library.persistence.repository.AuthorRepository;
import com.turkcell.library.exception.type.NotFoundException;

@Component
public class UpdateAuthorCommandHandler implements RequestHandler<UpdateAuthorCommand, UpdatedAuthorResponse> {
    private final AuthorRepository repository;
    
    public UpdateAuthorCommandHandler(AuthorRepository repository) {
        this.repository = repository;
    }

    @Override
    public UpdatedAuthorResponse handle(UpdateAuthorCommand request) {
        Integer id = request.getId();
        Author entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Author not found"));
        if (request.getFirstName() != null) {
            entity.setFirstName(request.getFirstName());
        }
        if (request.getLastName() != null) {
            entity.setLastName(request.getLastName());
        }
        entity = repository.save(entity);
        UpdatedAuthorResponse response = new UpdatedAuthorResponse();
        response.setId(entity.getId());
        response.setFirstName(entity.getFirstName());
        response.setLastName(entity.getLastName());
        return response;
    }
}
