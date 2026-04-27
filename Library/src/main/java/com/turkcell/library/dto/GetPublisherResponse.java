package com.turkcell.library.dto;

public class GetPublisherResponse {
    private Integer id;
    private String publisherName;

    public GetPublisherResponse() {}

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
