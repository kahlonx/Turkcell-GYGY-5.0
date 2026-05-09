package com.turkcell.library.application.features.fine.command.delete;

import com.turkcell.library.core.mediator.cqrs.Request;

public class DeleteFineCommand implements Request<Void> {
    private Integer id;
    public DeleteFineCommand(Integer id) { this.id = id; }
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
}
