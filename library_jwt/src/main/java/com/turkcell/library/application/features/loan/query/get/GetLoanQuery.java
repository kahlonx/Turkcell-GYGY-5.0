package com.turkcell.library.application.features.loan.query.get;

import com.turkcell.library.core.mediator.cqrs.Request;


public class GetLoanQuery implements Request<GetLoanResponse> {
    private Integer id;
    public GetLoanQuery(Integer id) { this.id = id; }
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
}
