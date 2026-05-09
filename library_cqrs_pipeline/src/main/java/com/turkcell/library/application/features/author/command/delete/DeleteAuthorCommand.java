package com.turkcell.library.application.features.author.command.delete;

import com.turkcell.library.core.mediator.cqrs.Request;

public class DeleteAuthorCommand implements Request<Void> {
    private Integer id;
    public DeleteAuthorCommand(Integer id) { this.id = id; }
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
}
