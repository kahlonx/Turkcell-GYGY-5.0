package com.turkcell.library.application.features.category.command.create;

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
