package com.turkcell.library.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;

import com.turkcell.library.service.LoanService;
import com.turkcell.library.dto.*;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService service;

    public LoanController(LoanService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedLoanResponse create(@RequestBody CreateLoanRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<ListLoanResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetLoanResponse getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public UpdatedLoanResponse update(@PathVariable Integer id, @RequestBody UpdateLoanRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
