package com.turkcell.library.application.features.finepayment.command.update;

import com.turkcell.library.core.mediator.cqrs.Request;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class UpdateFinePaymentCommand implements Request<UpdatedFinePaymentResponse> {
    private Integer id;
    @NotNull
    private BigDecimal paymentAmount;

    public UpdateFinePaymentCommand() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public BigDecimal getPaymentAmount() { return paymentAmount; }
    public void setPaymentAmount(BigDecimal paymentAmount) { this.paymentAmount = paymentAmount; }
}
