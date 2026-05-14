package com.turkcell.library.application.features.author.command.create;

import com.turkcell.library.core.mediator.cqrs.RequestHandler;
import org.springframework.stereotype.Component;
import com.turkcell.library.domain.entity.Author;
import com.turkcell.library.persistence.repository.AuthorRepository;

@Component
public class CreateAuthorCommandHandler implements RequestHandler<CreateAuthorCommand, CreatedAuthorResponse> {
    private final AuthorRepository repository;
    
    public CreateAuthorCommandHandler(AuthorRepository repository) {
        this.repository = repository;
    }

    @Override
    public CreatedAuthorResponse handle(CreateAuthorCommand request) {
        Author entity = new Author();
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity = repository.save(entity);
        CreatedAuthorResponse response = new CreatedAuthorResponse();
        response.setId(entity.getId());
        response.setFirstName(entity.getFirstName());
        response.setLastName(entity.getLastName());
        return response;
    }
}
