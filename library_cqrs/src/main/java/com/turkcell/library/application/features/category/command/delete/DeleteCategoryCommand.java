package com.turkcell.library.application.features.category.command.delete;

import com.turkcell.library.core.mediator.cqrs.Request;

public class DeleteCategoryCommand implements Request<Void> {
    private Integer id;

    public DeleteCategoryCommand(Integer id) { this.id = id; }
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
}
