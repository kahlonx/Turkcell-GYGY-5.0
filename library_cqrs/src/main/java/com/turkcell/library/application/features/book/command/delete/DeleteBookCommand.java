package com.turkcell.library.application.features.book.command.delete;

import com.turkcell.library.core.mediator.cqrs.Request;

public class DeleteBookCommand implements Request<Void> {
    private Integer id;
    public DeleteBookCommand(Integer id) { this.id = id; }
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
}
