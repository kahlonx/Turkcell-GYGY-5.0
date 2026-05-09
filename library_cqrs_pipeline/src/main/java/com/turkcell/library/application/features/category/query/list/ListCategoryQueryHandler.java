package com.turkcell.library.application.features.category.query.list;

import com.turkcell.library.core.mediator.cqrs.RequestHandler;
import com.turkcell.library.domain.entity.Category;
import com.turkcell.library.persistence.repository.CategoryRepository;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.ArrayList;

@Component
public class ListCategoryQueryHandler implements RequestHandler<ListCategoryQuery, List<ListCategoryResponse>> {
    private final CategoryRepository repository;
    
    public ListCategoryQueryHandler(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ListCategoryResponse> handle(ListCategoryQuery request) {
        List<Category> entities = repository.findAll();
        List<ListCategoryResponse> responses = new ArrayList<>();
        for (Category entity : entities) {
            ListCategoryResponse response = new ListCategoryResponse();
            response.setId(entity.getId());
            response.setCategoryName(entity.getCategoryName());

            responses.add(response);
        }
        return responses;
    }
}
