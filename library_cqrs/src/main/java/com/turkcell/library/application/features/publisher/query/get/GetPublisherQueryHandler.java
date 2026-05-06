package com.turkcell.library.application.features.publisher.query.get;

import com.turkcell.library.core.mediator.cqrs.RequestHandler;
import org.springframework.stereotype.Component;
import com.turkcell.library.domain.entity.Publisher;
import com.turkcell.library.persistence.repository.PublisherRepository;

@Component
public class GetPublisherQueryHandler implements RequestHandler<GetPublisherQuery, GetPublisherResponse> {
    private final PublisherRepository repository;
    
    public GetPublisherQueryHandler(PublisherRepository repository) {
        this.repository = repository;
    }

    @Override
    public GetPublisherResponse handle(GetPublisherQuery request) {
        Integer id = request.getId();
        Publisher entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Entity not found"));
        GetPublisherResponse response = new GetPublisherResponse();
        response.setId(entity.getId());
        response.setPublisherName(entity.getPublisherName());
        return response;
    }
}
