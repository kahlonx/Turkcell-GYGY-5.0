package com.turkcell.spring_starter.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

// Importing all the necessary DTOs
import com.turkcell.spring_starter.dto.CreateCategoryRequest;
import com.turkcell.spring_starter.dto.CreatedCategoryResponse;
import com.turkcell.spring_starter.dto.ListCategoryResponse;
import com.turkcell.spring_starter.dto.GetCategoryResponse;
import com.turkcell.spring_starter.dto.UpdateCategoryRequest;
import com.turkcell.spring_starter.dto.UpdatedCategoryResponse;

import com.turkcell.spring_starter.entity.Category;
import com.turkcell.spring_starter.repository.CategoryRepository;

@Service
public class CategoryServiceImpl {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CreatedCategoryResponse create(CreateCategoryRequest createCategoryRequest) {
        // Veritabanında insert-update çalıştır.
        // entity id'e sahipse update, null ise insert
        Category category = new Category();
        category.setName(createCategoryRequest.getName());

        category = this.categoryRepository.save(category); // ekledikten sonraki halini al

        CreatedCategoryResponse response = new CreatedCategoryResponse();
        response.setId(category.getId());
        response.setName(category.getName());

        return response;
    } 

    public List<ListCategoryResponse> getAll() {
        List<Category> categories = categoryRepository.findAll();

        // TODO: Refactor (e.g., using Streams or ModelMapper later on)
        List<ListCategoryResponse> responseList = new ArrayList<>();

        for (Category category : categories) {
            ListCategoryResponse response = new ListCategoryResponse();
            response.setId(category.getId());
            response.setName(category.getName());
            responseList.add(response);
        }

        return responseList;
    }

    public GetCategoryResponse getById(UUID id) {
        // Find the category or throw an exception if it doesn't exist
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        
        GetCategoryResponse response = new GetCategoryResponse();
        response.setId(category.getId());
        response.setName(category.getName());
        
        return response;
    }

    public UpdatedCategoryResponse update(UUID id, UpdateCategoryRequest request) {
        // First, verify the category exists
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        
        // Update the fields
        category.setName(request.getName());
        
        // Save the updated entity
        category = categoryRepository.save(category);

        // Map back to response DTO
        UpdatedCategoryResponse response = new UpdatedCategoryResponse();
        response.setId(category.getId());
        response.setName(category.getName());
        
        return response;
    }

    public void delete(UUID id) {
        // Spring Data JPA makes deletion very simple
        categoryRepository.deleteById(id);
    }
}