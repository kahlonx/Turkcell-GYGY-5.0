package com.turkcell.library.application.features.category.query.get;

import com.turkcell.library.core.mediator.cqrs.Request;
import com.turkcell.library.core.security.authorization.AuthorizableRequest;

import java.util.List;

public class GetCategoryQuery implements Request<GetCategoryResponse>, AuthorizableRequest {

    private Integer id;

    public GetCategoryQuery(Integer id) { this.id = id; }
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    @Override
    public List<String> getRequiredRoles() {
        return List.of();
    }
}
