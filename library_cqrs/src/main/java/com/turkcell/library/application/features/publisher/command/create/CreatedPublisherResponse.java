package com.turkcell.library.application.features.publisher.command.create;

public class CreatedPublisherResponse {
    private Integer id;
    private String publisherName;

    public CreatedPublisherResponse() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

}
