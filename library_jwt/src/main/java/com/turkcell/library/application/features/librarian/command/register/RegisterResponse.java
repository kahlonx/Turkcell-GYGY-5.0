package com.turkcell.library.application.features.librarian.command.register;

public class RegisterResponse {
    private Integer id;
    private String email;

    public RegisterResponse() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
