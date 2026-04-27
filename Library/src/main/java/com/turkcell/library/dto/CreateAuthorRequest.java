package com.turkcell.library.dto;

public class CreateAuthorRequest {
    private String firstName;
    private String lastName;

    public CreateAuthorRequest() {}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
