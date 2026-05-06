package com.turkcell.library.application.features.publisher.query.list;

import com.turkcell.library.core.mediator.cqrs.RequestHandler;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.ArrayList;
import com.turkcell.library.domain.entity.Publisher;
import com.turkcell.library.persistence.repository.PublisherRepository;

@Component
public class ListPublisherQueryHandler implements RequestHandler<ListPublisherQuery, List<ListPublisherResponse>> {
    private final PublisherRepository repository;
    
    public ListPublisherQueryHandler(PublisherRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ListPublisherResponse> handle(ListPublisherQuery request) {
        List<Publisher> entities = repository.findAll();
        List<ListPublisherResponse> responses = new ArrayList<>();
        for (Publisher entity : entities) {
            ListPublisherResponse response = new ListPublisherResponse();
            response.setId(entity.getId());
            response.setPublisherName(entity.getPublisherName());

            responses.add(response);
        }
        return responses;
    }
}
