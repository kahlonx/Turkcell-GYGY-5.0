package com.turkcell.spring_starter.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Importing all DTOs from the category domain
import com.turkcell.spring_starter.dto.CreateCategoryRequest;
import com.turkcell.spring_starter.dto.CreatedCategoryResponse;
import com.turkcell.spring_starter.dto.ListCategoryResponse;
import com.turkcell.spring_starter.dto.GetCategoryResponse;
import com.turkcell.spring_starter.dto.UpdateCategoryRequest;
import com.turkcell.spring_starter.dto.UpdatedCategoryResponse;

import com.turkcell.spring_starter.service.CategoryServiceImpl;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

// Bu projedeki tüm entityler için tüm CRUD işlemleri kodlanmalı.
// GET-GET BY ID-ADD-UPDATE-DELETE
// Kütüphane sisteminizi code-first oluşturun.
// JPQL  

@RestController
@RequestMapping("/api/categories")
public class CategoriesController {
    
    private final CategoryServiceImpl categoryServiceImpl;

    public CategoriesController(CategoryServiceImpl categoryServiceImpl) {
        this.categoryServiceImpl = categoryServiceImpl;
    }

    // CREATE (ADD)
    @PostMapping()
    public CreatedCategoryResponse create(@RequestBody CreateCategoryRequest createCategoryRequest) {
       return categoryServiceImpl.create(createCategoryRequest);
    }

    // GET ALL (LIST)
    @GetMapping
    public List<ListCategoryResponse> getAll() {
        return categoryServiceImpl.getAll();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public GetCategoryResponse getById(@PathVariable UUID id) {
        return categoryServiceImpl.getById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public UpdatedCategoryResponse update(@PathVariable UUID id, @RequestBody UpdateCategoryRequest updateCategoryRequest) {
        return categoryServiceImpl.update(id, updateCategoryRequest);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        categoryServiceImpl.delete(id);
    }
}