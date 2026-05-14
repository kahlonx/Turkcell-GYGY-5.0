package com.turkcell.library.application.features.book.query.list;

import com.turkcell.library.core.mediator.cqrs.RequestHandler;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;
import com.turkcell.library.domain.entity.Book;
import com.turkcell.library.persistence.repository.BookRepository;

@Component
public class ListBookQueryHandler implements RequestHandler<ListBookQuery, List<ListBookResponse>> {
    private final BookRepository repository;
    
    public ListBookQueryHandler(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ListBookResponse> handle(ListBookQuery request) {
        List<Book> entities = repository.findAll();
        List<ListBookResponse> responses = new ArrayList<>();
        for (Book entity : entities) {
            ListBookResponse response = new ListBookResponse();
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

            responses.add(response);
        }
        return responses;
    }
}
