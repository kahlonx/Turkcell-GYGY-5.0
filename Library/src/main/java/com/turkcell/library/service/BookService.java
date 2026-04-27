package com.turkcell.library.service;

import com.turkcell.library.dto.*;
import java.util.List;

public interface BookService {
    CreatedBookResponse create(CreateBookRequest request);
    List<ListBookResponse> getAll();
    GetBookResponse getById(Integer id);
    UpdatedBookResponse update(Integer id, UpdateBookRequest request);
    void delete(Integer id);
}
