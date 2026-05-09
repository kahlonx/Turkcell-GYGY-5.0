package com.turkcell.library.application.features.publisher.query.list;

public class ListPublisherResponse {
    private Integer id;
    private String publisherName;

    public ListPublisherResponse() {}

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
