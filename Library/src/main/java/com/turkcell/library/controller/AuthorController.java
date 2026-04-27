package com.turkcell.library.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;

import com.turkcell.library.service.AuthorService;
import com.turkcell.library.dto.*;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService service;

    public AuthorController(AuthorService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedAuthorResponse create(@RequestBody CreateAuthorRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<ListAuthorResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetAuthorResponse getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public UpdatedAuthorResponse update(@PathVariable Integer id, @RequestBody UpdateAuthorRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
