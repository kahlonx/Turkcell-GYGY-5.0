package com.turkcell.library.application.features.member.query.get;

import com.turkcell.library.core.mediator.cqrs.Request;


public class GetMemberQuery implements Request<GetMemberResponse> {
    private Integer id;
    public GetMemberQuery(Integer id) { this.id = id; }
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
}
