package com.turkcell.library.application.features.category.query.get;

public class GetCategoryResponse {
    private Integer id;
    private String categoryName;

    public GetCategoryResponse() {}

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
