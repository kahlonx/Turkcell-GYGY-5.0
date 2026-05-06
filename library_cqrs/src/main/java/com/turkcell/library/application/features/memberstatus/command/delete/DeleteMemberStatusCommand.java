package com.turkcell.library.application.features.memberstatus.command.delete;

import com.turkcell.library.core.mediator.cqrs.Request;

public class DeleteMemberStatusCommand implements Request<Void> {
    private Integer id;
    public DeleteMemberStatusCommand(Integer id) { this.id = id; }
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
}
