package com.turkcell.library.application.features.librarian.query.get;

import com.turkcell.library.core.mediator.cqrs.Request;


public class GetLibrarianQuery implements Request<GetLibrarianResponse> {
    private Integer id;
    public GetLibrarianQuery(Integer id) { this.id = id; }
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
}
