package com.turkcell.library.application.features.finepayment.command.create;

import com.turkcell.library.core.mediator.cqrs.Request;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class CreateFinePaymentCommand implements Request<CreatedFinePaymentResponse> {
    @NotNull
    private Integer memberId;
    @NotNull
    private BigDecimal paymentAmount;

    public CreateFinePaymentCommand() {}

    public Integer getMemberId() { return memberId; }
    public void setMemberId(Integer memberId) { this.memberId = memberId; }
    public BigDecimal getPaymentAmount() { return paymentAmount; }
    public void setPaymentAmount(BigDecimal paymentAmount) { this.paymentAmount = paymentAmount; }
}
