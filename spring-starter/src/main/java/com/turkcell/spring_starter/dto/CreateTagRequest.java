package com.turkcell.spring_starter.dto;

public class CreateTagRequest { // Rename to UpdateTagRequest for the update DTO
    private String name;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}