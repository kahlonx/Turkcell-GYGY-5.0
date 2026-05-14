package com.turkcell.library.application.features.loan.command.update;

import com.turkcell.library.core.mediator.cqrs.Request;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class UpdateLoanCommand implements Request<UpdatedLoanResponse> {
    private Integer id;
    @NotNull
    private LocalDate returnDate;

    public UpdateLoanCommand() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }
}
