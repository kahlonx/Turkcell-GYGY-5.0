package com.turkcell.library.service;

import com.turkcell.library.dto.*;
import java.util.List;

public interface LibrarianService {
    CreatedLibrarianResponse create(CreateLibrarianRequest request);
    List<ListLibrarianResponse> getAll();
    GetLibrarianResponse getById(Integer id);
    UpdatedLibrarianResponse update(Integer id, UpdateLibrarianRequest request);
    void delete(Integer id);
}
