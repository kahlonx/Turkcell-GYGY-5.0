package com.turkcell.library.application.features.book.query.get;

import com.turkcell.library.core.mediator.cqrs.Request;


public class GetBookQuery implements Request<GetBookResponse> {
    private Integer id;
    public GetBookQuery(Integer id) { this.id = id; }
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
}
