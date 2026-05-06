package com.turkcell.library.application.features.category.command.delete;

import com.turkcell.library.core.mediator.cqrs.RequestHandler;
import com.turkcell.library.persistence.repository.CategoryRepository;
import org.springframework.stereotype.Component;

@Component
public class DeleteCategoryCommandHandler implements RequestHandler<DeleteCategoryCommand, Void> {
    private final CategoryRepository repository;
    
    public DeleteCategoryCommandHandler(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Void handle(DeleteCategoryCommand request) {
        repository.deleteById(request.getId());
        return null;
    }
}
