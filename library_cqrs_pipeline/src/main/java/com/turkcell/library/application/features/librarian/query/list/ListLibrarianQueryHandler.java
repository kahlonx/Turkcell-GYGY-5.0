package com.turkcell.library.application.features.librarian.query.list;

import com.turkcell.library.core.mediator.cqrs.RequestHandler;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.ArrayList;
import com.turkcell.library.domain.entity.Librarian;
import com.turkcell.library.persistence.repository.LibrarianRepository;

@Component
public class ListLibrarianQueryHandler implements RequestHandler<ListLibrarianQuery, List<ListLibrarianResponse>> {
    private final LibrarianRepository repository;
    
    public ListLibrarianQueryHandler(LibrarianRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ListLibrarianResponse> handle(ListLibrarianQuery request) {
        List<Librarian> entities = repository.findAll();
        List<ListLibrarianResponse> responses = new ArrayList<>();
        for (Librarian entity : entities) {
            ListLibrarianResponse response = new ListLibrarianResponse();
            response.setId(entity.getId());
            response.setFirstName(entity.getFirstName());
            response.setLastName(entity.getLastName());

            responses.add(response);
        }
        return responses;
    }
}
