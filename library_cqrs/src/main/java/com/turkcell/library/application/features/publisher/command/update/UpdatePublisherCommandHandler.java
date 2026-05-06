package com.turkcell.library.application.features.publisher.command.update;

import com.turkcell.library.core.mediator.cqrs.RequestHandler;
import org.springframework.stereotype.Component;
import com.turkcell.library.domain.entity.Publisher;
import com.turkcell.library.persistence.repository.PublisherRepository;

@Component
public class UpdatePublisherCommandHandler implements RequestHandler<UpdatePublisherCommand, UpdatedPublisherResponse> {
    private final PublisherRepository repository;
    
    public UpdatePublisherCommandHandler(PublisherRepository repository) {
        this.repository = repository;
    }

    @Override
    public UpdatedPublisherResponse handle(UpdatePublisherCommand request) {
        Integer id = request.getId();
        Publisher entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Entity not found"));
        if (request.getPublisherName() != null) {
            entity.setPublisherName(request.getPublisherName());
        }
        entity = repository.save(entity);
        UpdatedPublisherResponse response = new UpdatedPublisherResponse();
        response.setId(entity.getId());
        response.setPublisherName(entity.getPublisherName());
        return response;
    }
}
