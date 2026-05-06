package com.turkcell.library.application.features.loan.command.create;

import com.turkcell.library.core.mediator.cqrs.Request;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class CreateLoanCommand implements Request<CreatedLoanResponse> {
    @NotNull
    private Integer bookId;
    @NotNull
    private Integer memberId;
    @NotNull
    private LocalDate loanDate;
    @NotNull
    private Integer librarianId;

    public CreateLoanCommand() {}

    public Integer getBookId() { return bookId; }
    public void setBookId(Integer bookId) { this.bookId = bookId; }
    public Integer getMemberId() { return memberId; }
    public void setMemberId(Integer memberId) { this.memberId = memberId; }
    public LocalDate getLoanDate() { return loanDate; }
    public void setLoanDate(LocalDate loanDate) { this.loanDate = loanDate; }
    public Integer getLibrarianId() { return librarianId; }
    public void setLibrarianId(Integer librarianId) { this.librarianId = librarianId; }
}
