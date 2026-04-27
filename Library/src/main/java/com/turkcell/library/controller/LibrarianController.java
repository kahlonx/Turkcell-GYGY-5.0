package com.turkcell.library.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;

import com.turkcell.library.service.LibrarianService;
import com.turkcell.library.dto.*;

@RestController
@RequestMapping("/api/librarians")
public class LibrarianController {

    private final LibrarianService service;

    public LibrarianController(LibrarianService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedLibrarianResponse create(@RequestBody CreateLibrarianRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<ListLibrarianResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetLibrarianResponse getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public UpdatedLibrarianResponse update(@PathVariable Integer id, @RequestBody UpdateLibrarianRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
