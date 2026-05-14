package com.turkcell.library.application.features.librarian.query.get;

import com.turkcell.library.core.mediator.cqrs.RequestHandler;
import org.springframework.stereotype.Component;
import com.turkcell.library.domain.entity.Librarian;
import com.turkcell.library.persistence.repository.LibrarianRepository;
import com.turkcell.library.exception.type.NotFoundException;

@Component
public class GetLibrarianQueryHandler implements RequestHandler<GetLibrarianQuery, GetLibrarianResponse> {
    private final LibrarianRepository repository;

    public GetLibrarianQueryHandler(LibrarianRepository repository) {
        this.repository = repository;
    }

    @Override
    public GetLibrarianResponse handle(GetLibrarianQuery request) {
        Integer id = request.getId();
        Librarian entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Librarian not found"));
        GetLibrarianResponse response = new GetLibrarianResponse();
        response.setId(entity.getId());
        response.setFirstName(entity.getFirstName());
        response.setLastName(entity.getLastName());
        return response;
    }
}
