package com.turkcell.library.application.features.author.query.list;

public class ListAuthorResponse {
    private Integer id;
    private String firstName;
    private String lastName;

    public ListAuthorResponse() {}

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
