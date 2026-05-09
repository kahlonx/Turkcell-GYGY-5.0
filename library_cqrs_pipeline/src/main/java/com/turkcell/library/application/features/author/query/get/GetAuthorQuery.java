package com.turkcell.library.application.features.author.query.get;

import com.turkcell.library.core.mediator.cqrs.Request;


public class GetAuthorQuery implements Request<GetAuthorResponse> {
    private Integer id;
    public GetAuthorQuery(Integer id) { this.id = id; }
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
}
