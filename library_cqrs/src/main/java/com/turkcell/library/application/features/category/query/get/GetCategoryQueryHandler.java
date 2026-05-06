package com.turkcell.library.application.features.category.query.get;

import com.turkcell.library.core.mediator.cqrs.RequestHandler;
import com.turkcell.library.domain.entity.Category;
import com.turkcell.library.persistence.repository.CategoryRepository;
import com.turkcell.library.exception.type.NotFoundException;
import org.springframework.stereotype.Component;

@Component
public class GetCategoryQueryHandler implements RequestHandler<GetCategoryQuery, GetCategoryResponse> {
    private final CategoryRepository repository;
    
    public GetCategoryQueryHandler(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public GetCategoryResponse handle(GetCategoryQuery request) {
        Category entity = repository.findById(request.getId()).orElseThrow(() -> new NotFoundException("Category not found"));
        GetCategoryResponse response = new GetCategoryResponse();
        response.setId(entity.getId());
        response.setCategoryName(entity.getCategoryName());
        return response;
    }
}
