package com.turkcell.library.application.features.publisher.command.create;

import com.turkcell.library.core.mediator.cqrs.RequestHandler;
import org.springframework.stereotype.Component;
import com.turkcell.library.domain.entity.Publisher;
import com.turkcell.library.persistence.repository.PublisherRepository;

@Component
public class CreatePublisherCommandHandler implements RequestHandler<CreatePublisherCommand, CreatedPublisherResponse> {
    private final PublisherRepository repository;
    
    public CreatePublisherCommandHandler(PublisherRepository repository) {
        this.repository = repository;
    }

    @Override
    public CreatedPublisherResponse handle(CreatePublisherCommand request) {
        Publisher entity = new Publisher();
        entity.setPublisherName(request.getPublisherName());
        entity = repository.save(entity);
        CreatedPublisherResponse response = new CreatedPublisherResponse();
        response.setId(entity.getId());
        response.setPublisherName(entity.getPublisherName());
        return response;
    }
}
