package com.turkcell.library.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;

import com.turkcell.library.service.FineService;
import com.turkcell.library.dto.*;

@RestController
@RequestMapping("/api/fines")
public class FineController {

    private final FineService service;

    public FineController(FineService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedFineResponse create(@RequestBody CreateFineRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<ListFineResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetFineResponse getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public UpdatedFineResponse update(@PathVariable Integer id, @RequestBody UpdateFineRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
