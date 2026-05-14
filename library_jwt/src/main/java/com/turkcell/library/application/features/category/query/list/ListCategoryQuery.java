package com.turkcell.library.application.features.category.query.list;

import com.turkcell.library.core.mediator.cqrs.Request;
import com.turkcell.library.core.security.authorization.AuthorizableRequest;

import java.util.List;

public class ListCategoryQuery implements Request<List<ListCategoryResponse>>, AuthorizableRequest {

    @Override
    public List<String> getRequiredRoles() {
        return List.of();
    }
}
