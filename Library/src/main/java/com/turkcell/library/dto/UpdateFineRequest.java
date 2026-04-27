package com.turkcell.library.dto;

import java.math.BigDecimal;

public class UpdateFineRequest {
    private BigDecimal fineAmount;

    public UpdateFineRequest() {}

    public BigDecimal getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(BigDecimal fineAmount) {
        this.fineAmount = fineAmount;
    }

}
