package com.turkcell.library.dto;

import java.math.BigDecimal;

public class CreateFinePaymentRequest {
    private Integer memberId;
    private BigDecimal paymentAmount;

    public CreateFinePaymentRequest() {}

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

}
