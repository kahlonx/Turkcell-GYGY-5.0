package com.turkcell.library.application.features.memberstatus.command.update;

import com.turkcell.library.core.mediator.cqrs.Request;
import jakarta.validation.constraints.NotBlank;

public class UpdateMemberStatusCommand implements Request<UpdatedMemberStatusResponse> {
    private Integer id;
    @NotBlank
    private String statusValue;

    public UpdateMemberStatusCommand() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getStatusValue() { return statusValue; }
    public void setStatusValue(String statusValue) { this.statusValue = statusValue; }
}
