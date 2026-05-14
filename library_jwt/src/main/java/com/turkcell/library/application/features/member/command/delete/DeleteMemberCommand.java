package com.turkcell.library.application.features.member.command.delete;

import com.turkcell.library.core.mediator.cqrs.Request;

public class DeleteMemberCommand implements Request<Void> {
    private Integer id;
    public DeleteMemberCommand(Integer id) { this.id = id; }
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
}
