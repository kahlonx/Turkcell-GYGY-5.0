package com.turkcell.library.application.features.author.command.create;

import com.turkcell.library.core.mediator.cqrs.Request;
import jakarta.validation.constraints.NotBlank;

public class CreateAuthorCommand implements Request<CreatedAuthorResponse> {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;

    public CreateAuthorCommand() {}

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
}
