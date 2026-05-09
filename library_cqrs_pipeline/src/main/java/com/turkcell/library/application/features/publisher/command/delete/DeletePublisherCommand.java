package com.turkcell.library.application.features.publisher.command.delete;

import com.turkcell.library.core.mediator.cqrs.Request;

public class DeletePublisherCommand implements Request<Void> {
    private Integer id;
    public DeletePublisherCommand(Integer id) { this.id = id; }
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
}
