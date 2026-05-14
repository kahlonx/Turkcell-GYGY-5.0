package com.turkcell.library.application.features.librarian.command.update;

import com.turkcell.library.core.mediator.cqrs.Request;
import jakarta.validation.constraints.NotBlank;

public class UpdateLibrarianCommand implements Request<UpdatedLibrarianResponse> {
    private Integer id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;

    public UpdateLibrarianCommand() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
}
