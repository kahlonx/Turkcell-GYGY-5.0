package com.turkcell.library.application.features.author.query.list;

import com.turkcell.library.core.mediator.cqrs.RequestHandler;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.ArrayList;
import com.turkcell.library.domain.entity.Author;
import com.turkcell.library.persistence.repository.AuthorRepository;

@Component
public class ListAuthorQueryHandler implements RequestHandler<ListAuthorQuery, List<ListAuthorResponse>> {
    private final AuthorRepository repository;
    
    public ListAuthorQueryHandler(AuthorRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ListAuthorResponse> handle(ListAuthorQuery request) {
        List<Author> entities = repository.findAll();
        List<ListAuthorResponse> responses = new ArrayList<>();
        for (Author entity : entities) {
            ListAuthorResponse response = new ListAuthorResponse();
            response.setId(entity.getId());
            response.setFirstName(entity.getFirstName());
            response.setLastName(entity.getLastName());

            responses.add(response);
        }
        return responses;
    }
}
