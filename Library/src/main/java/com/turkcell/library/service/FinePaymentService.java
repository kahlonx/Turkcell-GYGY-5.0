package com.turkcell.library.service;

import com.turkcell.library.dto.*;
import java.util.List;

public interface FinePaymentService {
    CreatedFinePaymentResponse create(CreateFinePaymentRequest request);
    List<ListFinePaymentResponse> getAll();
    GetFinePaymentResponse getById(Integer id);
    UpdatedFinePaymentResponse update(Integer id, UpdateFinePaymentRequest request);
    void delete(Integer id);
}
