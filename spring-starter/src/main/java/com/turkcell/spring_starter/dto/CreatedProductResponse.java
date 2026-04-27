package com.turkcell.spring_starter.dto;

import java.util.List;
import java.util.UUID;

public class CreatedProductResponse { // Rename class accordingly for the other 3 DTOs
    private UUID id;
    private String name;
    private String description;
    private UUID categoryId;
    private String categoryName;
    private List<String> tagNames; // Sending back tag names is usually helpful for frontends
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public UUID getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }
    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public List<String> getTagNames() {
        return tagNames;
    }
    public void setTagNames(List<String> tagNames) {
        this.tagNames = tagNames;
    }

}