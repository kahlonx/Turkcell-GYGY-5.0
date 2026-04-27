package com.turkcell.library.dto;

import java.math.BigDecimal;

public class UpdateFinePaymentRequest {
    private BigDecimal paymentAmount;

    public UpdateFinePaymentRequest() {}

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

}
