package com.turkcell.library.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class GetFinePaymentResponse {
    private Integer id;
    private Integer memberId;
    private BigDecimal paymentAmount;
    private LocalDateTime paymentDate;

    public GetFinePaymentResponse() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

}
