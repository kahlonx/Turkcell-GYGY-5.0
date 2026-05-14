package com.turkcell.library.application.features.category.command.update;

import com.turkcell.library.core.mediator.cqrs.RequestHandler;
import com.turkcell.library.domain.entity.Category;
import com.turkcell.library.persistence.repository.CategoryRepository;
import com.turkcell.library.exception.type.NotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UpdateCategoryCommandHandler implements RequestHandler<UpdateCategoryCommand, UpdatedCategoryResponse> {
    private final CategoryRepository repository;
    
    public UpdateCategoryCommandHandler(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public UpdatedCategoryResponse handle(UpdateCategoryCommand request) {
        Category entity = repository.findById(request.getId()).orElseThrow(() -> new NotFoundException("Category not found"));
        if (request.getCategoryName() != null) {
            entity.setCategoryName(request.getCategoryName());
        }
        entity = repository.save(entity);
        UpdatedCategoryResponse response = new UpdatedCategoryResponse();
        response.setId(entity.getId());
        response.setCategoryName(entity.getCategoryName());
        return response;
    }
}
