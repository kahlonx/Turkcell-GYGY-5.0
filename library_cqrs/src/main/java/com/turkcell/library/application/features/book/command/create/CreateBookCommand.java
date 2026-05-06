package com.turkcell.library.application.features.book.command.create;

import com.turkcell.library.core.mediator.cqrs.Request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class CreateBookCommand implements Request<CreatedBookResponse> {
    @NotBlank
    private String title;
    @NotNull
    private Integer categoryId;
    @NotNull
    private LocalDate publicationDate;
    @NotNull
    private Integer copiesOwned;
    @NotBlank
    private String barcodeNo;
    @NotNull
    private List<Integer> publisherIds;
    @NotNull
    private List<Integer> authorIds;

    public CreateBookCommand() {}

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public Integer getCategoryId() { return categoryId; }
    public void setCategoryId(Integer categoryId) { this.categoryId = categoryId; }
    public LocalDate getPublicationDate() { return publicationDate; }
    public void setPublicationDate(LocalDate publicationDate) { this.publicationDate = publicationDate; }
    public Integer getCopiesOwned() { return copiesOwned; }
    public void setCopiesOwned(Integer copiesOwned) { this.copiesOwned = copiesOwned; }
    public String getBarcodeNo() { return barcodeNo; }
    public void setBarcodeNo(String barcodeNo) { this.barcodeNo = barcodeNo; }
    public List<Integer> getPublisherIds() { return publisherIds; }
    public void setPublisherIds(List<Integer> publisherIds) { this.publisherIds = publisherIds; }
    public List<Integer> getAuthorIds() { return authorIds; }
    public void setAuthorIds(List<Integer> authorIds) { this.authorIds = authorIds; }
}
