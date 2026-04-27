package com.turkcell.library.dto;

import java.time.LocalDate;

public class UpdateLoanRequest {
    private LocalDate returnDate;

    public UpdateLoanRequest() {}

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

}
