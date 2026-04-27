package com.turkcell.library.service;

import com.turkcell.library.dto.*;
import java.util.List;

public interface CategoryService {
    CreatedCategoryResponse create(CreateCategoryRequest request);
    List<ListCategoryResponse> getAll();
    GetCategoryResponse getById(Integer id);
    UpdatedCategoryResponse update(Integer id, UpdateCategoryRequest request);
    void delete(Integer id);
}
