package com.turkcell.library.application.features.librarian.command.delete;

import com.turkcell.library.core.mediator.cqrs.Request;

public class DeleteLibrarianCommand implements Request<Void> {
    private Integer id;
    public DeleteLibrarianCommand(Integer id) { this.id = id; }
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
}
