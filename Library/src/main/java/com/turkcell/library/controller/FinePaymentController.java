package com.turkcell.library.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;

import com.turkcell.library.service.FinePaymentService;
import com.turkcell.library.dto.*;

@RestController
@RequestMapping("/api/fine-payments")
public class FinePaymentController {

    private final FinePaymentService service;

    public FinePaymentController(FinePaymentService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedFinePaymentResponse create(@RequestBody CreateFinePaymentRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<ListFinePaymentResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetFinePaymentResponse getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public UpdatedFinePaymentResponse update(@PathVariable Integer id, @RequestBody UpdateFinePaymentRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
