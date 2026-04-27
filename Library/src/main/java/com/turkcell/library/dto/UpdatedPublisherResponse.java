package com.turkcell.library.dto;

public class UpdatedPublisherResponse {
    private Integer id;
    private String publisherName;

    public UpdatedPublisherResponse() {}

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
