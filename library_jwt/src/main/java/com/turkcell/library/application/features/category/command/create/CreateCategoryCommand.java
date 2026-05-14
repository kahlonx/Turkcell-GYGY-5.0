package com.turkcell.library.application.features.category.command.create;

import com.turkcell.library.core.mediator.cqrs.Request;
import jakarta.validation.constraints.NotBlank;

public class CreateCategoryCommand implements Request<CreatedCategoryResponse> {
    @NotBlank
    private String categoryName;

    public CreateCategoryCommand() {}

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
}
