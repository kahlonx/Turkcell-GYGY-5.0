package com.turkcell.library.application.features.category.query.get;

import com.turkcell.library.core.mediator.cqrs.Request;


public class GetCategoryQuery implements Request<GetCategoryResponse> {
    private Integer id;

    public GetCategoryQuery(Integer id) { this.id = id; }
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
}
