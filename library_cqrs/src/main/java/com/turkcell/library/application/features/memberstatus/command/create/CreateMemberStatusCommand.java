package com.turkcell.library.application.features.memberstatus.command.create;

import com.turkcell.library.core.mediator.cqrs.Request;
import jakarta.validation.constraints.NotBlank;

public class CreateMemberStatusCommand implements Request<CreatedMemberStatusResponse> {
    @NotBlank
    private String statusValue;

    public CreateMemberStatusCommand() {}

    public String getStatusValue() { return statusValue; }
    public void setStatusValue(String statusValue) { this.statusValue = statusValue; }
}
