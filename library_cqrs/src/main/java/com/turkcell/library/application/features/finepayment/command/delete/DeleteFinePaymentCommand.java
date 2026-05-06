package com.turkcell.library.application.features.finepayment.command.delete;

import com.turkcell.library.core.mediator.cqrs.Request;

public class DeleteFinePaymentCommand implements Request<Void> {
    private Integer id;
    public DeleteFinePaymentCommand(Integer id) { this.id = id; }
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
}
