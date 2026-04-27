package com.turkcell.library.service;

import com.turkcell.library.dto.*;
import java.util.List;

public interface LoanService {
    CreatedLoanResponse create(CreateLoanRequest request);
    List<ListLoanResponse> getAll();
    GetLoanResponse getById(Integer id);
    UpdatedLoanResponse update(Integer id, UpdateLoanRequest request);
    void delete(Integer id);
}
