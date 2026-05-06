package com.turkcell.library.application.features.book.query.get;

import com.turkcell.library.core.mediator.cqrs.RequestHandler;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;
import com.turkcell.library.domain.entity.Book;
import com.turkcell.library.persistence.repository.BookRepository;
import com.turkcell.library.exception.type.NotFoundException;

@Component
public class GetBookQueryHandler implements RequestHandler<GetBookQuery, GetBookResponse> {
    private final BookRepository repository;
    
    public GetBookQueryHandler(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public GetBookResponse handle(GetBookQuery request) {
        Integer id = request.getId();
        Book entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Book not found"));
        GetBookResponse response = new GetBookResponse();
        response.setId(entity.getId());
        response.setTitle(entity.getTitle());
        if (entity.getCategory() != null) {
            response.setCategoryId(entity.getCategory().getId());
        }
        response.setPublicationDate(entity.getPublicationDate());
        response.setCopiesOwned(entity.getCopiesOwned());
        response.setBarcodeNo(entity.getBarcodeNo());
        if (entity.getPublishers() != null) {
            response.setPublisherIds(entity.getPublishers().stream().map(x -> x.getId()).collect(Collectors.toList()));
        }
        if (entity.getAuthors() != null) {
            response.setAuthorIds(entity.getAuthors().stream().map(x -> x.getId()).collect(Collectors.toList()));
        }
        return response;
    }
}
