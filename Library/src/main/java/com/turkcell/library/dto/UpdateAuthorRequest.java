package com.turkcell.library.dto;

public class UpdateAuthorRequest {
    private String firstName;
    private String lastName;

    public UpdateAuthorRequest() {}

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
