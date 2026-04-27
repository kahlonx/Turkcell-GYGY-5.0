package com.turkcell.library.dto;

import java.time.LocalDate;
import java.util.List;

public class CreatedBookResponse {
    private Integer id;
    private String title;
    private Integer categoryId;
    private LocalDate publicationDate;
    private Integer copiesOwned;
    private String barcodeNo;
    private List<Integer> publisherIds;
    private List<Integer> authorIds;

    public CreatedBookResponse() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Integer getCopiesOwned() {
        return copiesOwned;
    }

    public void setCopiesOwned(Integer copiesOwned) {
        this.copiesOwned = copiesOwned;
    }

    public String getBarcodeNo() {
        return barcodeNo;
    }

    public void setBarcodeNo(String barcodeNo) {
        this.barcodeNo = barcodeNo;
    }

    public List<Integer> getPublisherIds() {
        return publisherIds;
    }

    public void setPublisherIds(List<Integer> publisherIds) {
        this.publisherIds = publisherIds;
    }

    public List<Integer> getAuthorIds() {
        return authorIds;
    }

    public void setAuthorIds(List<Integer> authorIds) {
        this.authorIds = authorIds;
    }

}
