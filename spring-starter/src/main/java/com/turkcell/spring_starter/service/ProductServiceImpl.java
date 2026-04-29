package com.turkcell.spring_starter.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.turkcell.spring_starter.dto.*;
import com.turkcell.spring_starter.entity.Category;
import com.turkcell.spring_starter.entity.Product;
import com.turkcell.spring_starter.entity.Tag;
import com.turkcell.spring_starter.exception.CategoryNotFoundException;
import com.turkcell.spring_starter.exception.ProductNotFoundException;
import com.turkcell.spring_starter.repository.CategoryRepository;
import com.turkcell.spring_starter.repository.ProductRepository;
import com.turkcell.spring_starter.repository.TagRepository;

@Service
public class ProductServiceImpl {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;

    public ProductServiceImpl(ProductRepository productRepository, 
                              CategoryRepository categoryRepository, 
                              TagRepository tagRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.tagRepository = tagRepository;
    }

    public CreatedProductResponse create(CreateProductRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found!"));

        List<Tag> foundTags = tagRepository.findAllById(request.getTagIds());
        Set<Tag> tags = new HashSet<>(foundTags);

        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setCategory(category);
        product.setTags(tags);

        product = productRepository.save(product);

        CreatedProductResponse response = new CreatedProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setCategoryId(product.getCategory().getId());
        response.setCategoryName(product.getCategory().getName());
        
        // Convert the Set of Tag entities into a List of Strings (Tag names)
        List<String> tagNames = new ArrayList<>();
        for (Tag tag : product.getTags()) {
            tagNames.add(tag.getName());
        }
        response.setTagNames(tagNames);
        
        return response;
    }

    public List<ListProductResponse> getAll() {
        List<Product> products = productRepository.findAll();
        List<ListProductResponse> responseList = new ArrayList<>();

        for (Product product : products) {
            ListProductResponse response = new ListProductResponse();
            response.setId(product.getId());
            response.setName(product.getName());
            response.setDescription(product.getDescription());
            response.setCategoryName(product.getCategory().getName());
            
            List<String> tagNames = new ArrayList<>();
            for (Tag tag : product.getTags()) {
                tagNames.add(tag.getName());
            }
            response.setTagNames(tagNames);
            
            responseList.add(response);
        }
        return responseList;
    }

    public GetProductResponse getById(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found!"));
        
        GetProductResponse response = new GetProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setCategoryId(product.getCategory().getId());
        response.setCategoryName(product.getCategory().getName());
        
        List<String> tagNames = new ArrayList<>();
        for (Tag tag : product.getTags()) {
            tagNames.add(tag.getName());
        }
        response.setTagNames(tagNames);
        
        return response;
    }

    public UpdatedProductResponse update(UUID id, UpdateProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found!"));
        
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found!"));

        List<Tag> foundTags = tagRepository.findAllById(request.getTagIds());
        Set<Tag> tags = new HashSet<>(foundTags);

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setCategory(category);
        product.setTags(tags);

        product = productRepository.save(product);

        UpdatedProductResponse response = new UpdatedProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setCategoryId(product.getCategory().getId());
        response.setCategoryName(product.getCategory().getName());
        
        List<String> tagNames = new ArrayList<>();
        for (Tag tag : product.getTags()) {
            tagNames.add(tag.getName());
        }
        response.setTagNames(tagNames);
        
        return response;
    }

    public void delete(UUID id) {
        productRepository.deleteById(id);
    }
}