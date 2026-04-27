package com.turkcell.library.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import com.turkcell.library.dto.*;
import com.turkcell.library.entity.Category;
import com.turkcell.library.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;

    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public CreatedCategoryResponse create(CreateCategoryRequest request) {
        Category entity = new Category();
        entity.setCategoryName(request.getCategoryName());
        entity = repository.save(entity);
        CreatedCategoryResponse response = new CreatedCategoryResponse();
        response.setId(entity.getId());
        return response;
    }

    @Override
    public List<ListCategoryResponse> getAll() {
        List<Category> entities = repository.findAll();
        List<ListCategoryResponse> responses = new ArrayList<>();
        for (Category entity : entities) {
            ListCategoryResponse response = new ListCategoryResponse();
            response.setId(entity.getId());
            responses.add(response);
        }
        return responses;
    }

    @Override
    public GetCategoryResponse getById(Integer id) {
        Category entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Entity not found"));
        GetCategoryResponse response = new GetCategoryResponse();
        response.setId(entity.getId());
        return response;
    }

    @Override
    public UpdatedCategoryResponse update(Integer id, UpdateCategoryRequest request) {
        Category entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Entity not found"));
        if (request.getCategoryName() != null) {
            entity.setCategoryName(request.getCategoryName());
        }
        entity = repository.save(entity);
        UpdatedCategoryResponse response = new UpdatedCategoryResponse();
        response.setId(entity.getId());
        return response;
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
