package com.turkcell.library.application.features.librarian.command.create;

import com.turkcell.library.core.mediator.cqrs.Request;
import jakarta.validation.constraints.NotBlank;

public class CreateLibrarianCommand implements Request<CreatedLibrarianResponse> {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;

    public CreateLibrarianCommand() {}

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
}
