package com.turkcell.library.application.features.publisher.command.update;

import com.turkcell.library.core.mediator.cqrs.Request;
import jakarta.validation.constraints.NotBlank;

public class UpdatePublisherCommand implements Request<UpdatedPublisherResponse> {
    private Integer id;
    @NotBlank
    private String publisherName;

    public UpdatePublisherCommand() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getPublisherName() { return publisherName; }
    public void setPublisherName(String publisherName) { this.publisherName = publisherName; }
}
