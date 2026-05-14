package com.turkcell.library.application.features.author.command.delete;

import com.turkcell.library.core.mediator.cqrs.RequestHandler;
import org.springframework.stereotype.Component;
import com.turkcell.library.persistence.repository.AuthorRepository;

@Component
public class DeleteAuthorCommandHandler implements RequestHandler<DeleteAuthorCommand, Void> {
    private final AuthorRepository repository;
    
    public DeleteAuthorCommandHandler(AuthorRepository repository) {
        this.repository = repository;
    }

    @Override
    public Void handle(DeleteAuthorCommand request) {
        Integer id = request.getId();
        repository.deleteById(id);
        return null;
    }
}
