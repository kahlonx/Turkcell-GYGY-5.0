package com.turkcell.library.core.security.authorization;

import java.util.List;

public interface AuthorizableRequest {
    List<String> getRequiredRoles();
}
