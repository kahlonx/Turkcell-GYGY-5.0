package com.turkcell.spring_starter.dto;

import java.util.UUID;

public class CreatedTagResponse { // Rename class accordingly for the other 3 DTOs
    private UUID id;
    private String name;
    
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}