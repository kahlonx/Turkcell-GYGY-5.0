package com.turkcell.library.application.features.book.command.create;

import com.turkcell.library.core.mediator.cqrs.RequestHandler;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;
import com.turkcell.library.domain.entity.Book;
import com.turkcell.library.persistence.repository.BookRepository;
import com.turkcell.library.domain.entity.Category;
import com.turkcell.library.persistence.repository.CategoryRepository;
import com.turkcell.library.exception.type.NotFoundException;

@Component
public class CreateBookCommandHandler implements RequestHandler<CreateBookCommand, CreatedBookResponse> {
    private final BookRepository repository;
    private final CategoryRepository categoryRepository;
    
    public CreateBookCommandHandler(BookRepository repository, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CreatedBookResponse handle(CreateBookCommand request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Category not found"));
        Book entity = new Book();
        entity.setTitle(request.getTitle());
        entity.setPublicationDate(request.getPublicationDate());
        entity.setCopiesOwned(request.getCopiesOwned());
        entity.setBarcodeNo(request.getBarcodeNo());
        entity.setCategory(category);
        entity = repository.save(entity);
        CreatedBookResponse response = new CreatedBookResponse();
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
