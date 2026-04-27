package com.turkcell.library.service;

import com.turkcell.library.dto.*;
import java.util.List;

public interface AuthorService {
    CreatedAuthorResponse create(CreateAuthorRequest request);
    List<ListAuthorResponse> getAll();
    GetAuthorResponse getById(Integer id);
    UpdatedAuthorResponse update(Integer id, UpdateAuthorRequest request);
    void delete(Integer id);
}
