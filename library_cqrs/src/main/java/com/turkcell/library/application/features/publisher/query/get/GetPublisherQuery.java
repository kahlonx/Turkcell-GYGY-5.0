package com.turkcell.library.application.features.publisher.query.get;

import com.turkcell.library.core.mediator.cqrs.Request;


public class GetPublisherQuery implements Request<GetPublisherResponse> {
    private Integer id;
    public GetPublisherQuery(Integer id) { this.id = id; }
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
}
