package com.turkcell.library.application.features.author.query.get;

import com.turkcell.library.core.mediator.cqrs.RequestHandler;
import org.springframework.stereotype.Component;
import com.turkcell.library.domain.entity.Author;
import com.turkcell.library.persistence.repository.AuthorRepository;
import com.turkcell.library.exception.type.NotFoundException;

@Component
public class GetAuthorQueryHandler implements RequestHandler<GetAuthorQuery, GetAuthorResponse> {
    private final AuthorRepository repository;
    
    public GetAuthorQueryHandler(AuthorRepository repository) {
        this.repository = repository;
    }

    @Override
    public GetAuthorResponse handle(GetAuthorQuery request) {
        Integer id = request.getId();
        Author entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Author not found"));
        GetAuthorResponse response = new GetAuthorResponse();
        response.setId(entity.getId());
        response.setFirstName(entity.getFirstName());
        response.setLastName(entity.getLastName());
        return response;
    }
}
