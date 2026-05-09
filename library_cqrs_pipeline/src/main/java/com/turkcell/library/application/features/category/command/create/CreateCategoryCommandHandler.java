package com.turkcell.library.application.features.category.command.create;

import com.turkcell.library.core.mediator.cqrs.RequestHandler;
import com.turkcell.library.domain.entity.Category;
import com.turkcell.library.persistence.repository.CategoryRepository;
import org.springframework.stereotype.Component;

@Component
public class CreateCategoryCommandHandler implements RequestHandler<CreateCategoryCommand, CreatedCategoryResponse> {
    private final CategoryRepository repository;
    
    public CreateCategoryCommandHandler(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public CreatedCategoryResponse handle(CreateCategoryCommand request) {
        Category entity = new Category();
        entity.setCategoryName(request.getCategoryName());
        entity = repository.save(entity);
        CreatedCategoryResponse response = new CreatedCategoryResponse();
        response.setId(entity.getId());
        response.setCategoryName(entity.getCategoryName());
        return response;
    }
}
