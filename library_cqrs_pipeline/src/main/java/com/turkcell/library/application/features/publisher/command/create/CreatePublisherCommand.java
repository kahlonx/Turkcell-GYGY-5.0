package com.turkcell.library.application.features.publisher.command.create;

import com.turkcell.library.core.mediator.cqrs.Request;
import jakarta.validation.constraints.NotBlank;

public class CreatePublisherCommand implements Request<CreatedPublisherResponse> {
    @NotBlank
    private String publisherName;

    public CreatePublisherCommand() {}

    public String getPublisherName() { return publisherName; }
    public void setPublisherName(String publisherName) { this.publisherName = publisherName; }
}
