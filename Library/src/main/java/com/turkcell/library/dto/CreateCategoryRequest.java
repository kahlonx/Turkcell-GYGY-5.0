package com.turkcell.library.dto;

public class CreateCategoryRequest {
    private String categoryName;

    public CreateCategoryRequest() {}

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}
