package com.turkcell.library.application.features.memberstatus.query.get;

import com.turkcell.library.core.mediator.cqrs.Request;


public class GetMemberStatusQuery implements Request<GetMemberStatusResponse> {
    private Integer id;
    public GetMemberStatusQuery(Integer id) { this.id = id; }
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
}
