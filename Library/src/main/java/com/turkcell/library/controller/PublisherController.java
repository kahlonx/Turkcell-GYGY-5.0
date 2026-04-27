package com.turkcell.library.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;

import com.turkcell.library.service.PublisherService;
import com.turkcell.library.dto.*;

@RestController
@RequestMapping("/api/publishers")
public class PublisherController {

    private final PublisherService service;

    public PublisherController(PublisherService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedPublisherResponse create(@RequestBody CreatePublisherRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<ListPublisherResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetPublisherResponse getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public UpdatedPublisherResponse update(@PathVariable Integer id, @RequestBody UpdatePublisherRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
