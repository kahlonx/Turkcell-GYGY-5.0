package com.turkcell.library.application.features.book.command.delete;

import com.turkcell.library.core.mediator.cqrs.RequestHandler;
import org.springframework.stereotype.Component;
import com.turkcell.library.persistence.repository.BookRepository;

@Component
public class DeleteBookCommandHandler implements RequestHandler<DeleteBookCommand, Void> {
    private final BookRepository repository;
    
    public DeleteBookCommandHandler(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public Void handle(DeleteBookCommand request) {
        Integer id = request.getId();
        repository.deleteById(id);
        return null;
    }
}
