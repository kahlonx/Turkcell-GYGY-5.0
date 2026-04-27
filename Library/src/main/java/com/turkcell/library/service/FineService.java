package com.turkcell.library.service;

import com.turkcell.library.dto.*;
import java.util.List;

public interface FineService {
    CreatedFineResponse create(CreateFineRequest request);
    List<ListFineResponse> getAll();
    GetFineResponse getById(Integer id);
    UpdatedFineResponse update(Integer id, UpdateFineRequest request);
    void delete(Integer id);
}
