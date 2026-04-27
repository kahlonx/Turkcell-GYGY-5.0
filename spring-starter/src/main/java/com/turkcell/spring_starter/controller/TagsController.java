package com.turkcell.spring_starter.controller;

import org.springframework.web.bind.annotation.*;
import com.turkcell.spring_starter.dto.*;
import com.turkcell.spring_starter.service.TagServiceImpl;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tags")
public class TagsController {
    private final TagServiceImpl tagServiceImpl;

    public TagsController(TagServiceImpl tagServiceImpl) {
        this.tagServiceImpl = tagServiceImpl;
    }

    @PostMapping
    public CreatedTagResponse create(@RequestBody CreateTagRequest request) {
        return tagServiceImpl.create(request);
    }

    @GetMapping
    public List<ListTagResponse> getAll() {
        return tagServiceImpl.getAll();
    }

    @GetMapping("/{id}")
    public GetTagResponse getById(@PathVariable UUID id) {
        return tagServiceImpl.getById(id);
    }

    @PutMapping("/{id}")
    public UpdatedTagResponse update(@PathVariable UUID id, @RequestBody UpdateTagRequest request) {
        return tagServiceImpl.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        tagServiceImpl.delete(id);
    }
}