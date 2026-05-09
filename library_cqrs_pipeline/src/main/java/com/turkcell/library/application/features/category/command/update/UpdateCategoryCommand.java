package com.turkcell.library.application.features.category.command.update;

import com.turkcell.library.core.mediator.cqrs.Request;
import jakarta.validation.constraints.NotBlank;

public class UpdateCategoryCommand implements Request<UpdatedCategoryResponse> {
    private Integer id;
    @NotBlank
    private String categoryName;

    public UpdateCategoryCommand() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
}
