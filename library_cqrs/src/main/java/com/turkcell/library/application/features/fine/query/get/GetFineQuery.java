package com.turkcell.library.application.features.fine.query.get;

import com.turkcell.library.core.mediator.cqrs.Request;


public class GetFineQuery implements Request<GetFineResponse> {
    private Integer id;
    public GetFineQuery(Integer id) { this.id = id; }
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
}
