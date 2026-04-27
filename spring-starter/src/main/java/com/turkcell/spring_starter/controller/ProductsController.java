package com.turkcell.spring_starter.controller;

import org.springframework.web.bind.annotation.*;
import com.turkcell.spring_starter.dto.*;
import com.turkcell.spring_starter.service.ProductServiceImpl;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductsController {
    private final ProductServiceImpl productServiceImpl;

    public ProductsController(ProductServiceImpl productServiceImpl) {
        this.productServiceImpl = productServiceImpl;
    }

    @PostMapping
    public CreatedProductResponse create(@RequestBody CreateProductRequest request) {
        return productServiceImpl.create(request);
    }

    @GetMapping
    public List<ListProductResponse> getAll() {
        return productServiceImpl.getAll();
    }

    @GetMapping("/{id}")
    public GetProductResponse getById(@PathVariable UUID id) {
        return productServiceImpl.getById(id);
    }

    @PutMapping("/{id}")
    public UpdatedProductResponse update(@PathVariable UUID id, @RequestBody UpdateProductRequest request) {
        return productServiceImpl.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        productServiceImpl.delete(id);
    }
}