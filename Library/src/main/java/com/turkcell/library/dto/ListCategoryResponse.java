package com.turkcell.library.dto;

public class ListCategoryResponse {
    private Integer id;
    private String categoryName;

    public ListCategoryResponse() {}

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
