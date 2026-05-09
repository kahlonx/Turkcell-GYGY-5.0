package com.turkcell.library.application.features.finepayment.query.get;

import com.turkcell.library.core.mediator.cqrs.Request;


public class GetFinePaymentQuery implements Request<GetFinePaymentResponse> {
    private Integer id;
    public GetFinePaymentQuery(Integer id) { this.id = id; }
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
}
