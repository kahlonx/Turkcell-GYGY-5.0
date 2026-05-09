package com.turkcell.library.application.features.loan.command.delete;

import com.turkcell.library.core.mediator.cqrs.Request;

public class DeleteLoanCommand implements Request<Void> {
    private Integer id;
    public DeleteLoanCommand(Integer id) { this.id = id; }
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
}
