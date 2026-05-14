package com.turkcell.library.application.features.fine.command.update;

import com.turkcell.library.core.mediator.cqrs.Request;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class UpdateFineCommand implements Request<UpdatedFineResponse> {
    private Integer id;
    @NotNull
    private BigDecimal fineAmount;

    public UpdateFineCommand() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public BigDecimal getFineAmount() { return fineAmount; }
    public void setFineAmount(BigDecimal fineAmount) { this.fineAmount = fineAmount; }
}
