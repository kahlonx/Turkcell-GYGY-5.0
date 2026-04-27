package com.turkcell.library.dto;

public class CreatedCategoryResponse {
    private Integer id;
    private String categoryName;

    public CreatedCategoryResponse() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}
