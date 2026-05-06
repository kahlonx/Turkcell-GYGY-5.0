package com.turkcell.library.application.features.book.command.update;

import com.turkcell.library.core.mediator.cqrs.RequestHandler;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;
import com.turkcell.library.domain.entity.Book;
import com.turkcell.library.persistence.repository.BookRepository;
import com.turkcell.library.domain.entity.Category;
import com.turkcell.library.persistence.repository.CategoryRepository;
import com.turkcell.library.exception.type.NotFoundException;

@Component
public class UpdateBookCommandHandler implements RequestHandler<UpdateBookCommand, UpdatedBookResponse> {
    private final BookRepository repository;
    private final CategoryRepository categoryRepository;
    
    public UpdateBookCommandHandler(BookRepository repository, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public UpdatedBookResponse handle(UpdateBookCommand request) {
        Integer id = request.getId();
        Book entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Book not found"));
        if (request.getCategoryId() != null) {
            Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new NotFoundException("Category not found"));
            entity.setCategory(category);
        }
        if (request.getTitle() != null) {
            entity.setTitle(request.getTitle());
        }
        if (request.getPublicationDate() != null) {
            entity.setPublicationDate(request.getPublicationDate());
        }
        if (request.getCopiesOwned() != null) {
            entity.setCopiesOwned(request.getCopiesOwned());
        }
        if (request.getBarcodeNo() != null) {
            entity.setBarcodeNo(request.getBarcodeNo());
        }
        entity = repository.save(entity);
        UpdatedBookResponse response = new UpdatedBookResponse();
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
