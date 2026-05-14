package com.turkcell.library.application.features.author.query.get;

public class GetAuthorResponse {
    private Integer id;
    private String firstName;
    private String lastName;

    public GetAuthorResponse() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
