package com.turkcell.library.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;

import com.turkcell.library.service.BookService;
import com.turkcell.library.dto.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedBookResponse create(@RequestBody CreateBookRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<ListBookResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetBookResponse getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public UpdatedBookResponse update(@PathVariable Integer id, @RequestBody UpdateBookRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
