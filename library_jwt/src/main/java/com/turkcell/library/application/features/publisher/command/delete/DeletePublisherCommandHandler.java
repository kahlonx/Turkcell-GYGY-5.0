package com.turkcell.library.application.features.publisher.command.delete;

import com.turkcell.library.core.mediator.cqrs.RequestHandler;
import org.springframework.stereotype.Component;
import com.turkcell.library.persistence.repository.PublisherRepository;

@Component
public class DeletePublisherCommandHandler implements RequestHandler<DeletePublisherCommand, Void> {
    private final PublisherRepository repository;
    
    public DeletePublisherCommandHandler(PublisherRepository repository) {
        this.repository = repository;
    }

    @Override
    public Void handle(DeletePublisherCommand request) {
        Integer id = request.getId();
        repository.deleteById(id);
        return null;
    }
}
