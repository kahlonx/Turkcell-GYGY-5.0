package com.turkcell.library.core.security.context;

import java.util.List;

public class UserContext {

    private static final ThreadLocal<UserContext> CONTEXT = new ThreadLocal<>();

    private final String email;
    private final List<String> roles;

    public UserContext(String email, List<String> roles) {
        this.email = email;
        this.roles = roles != null ? roles : List.of();
    }

    public static UserContext getCurrentUser() { return CONTEXT.get(); }
    public static void setCurrentUser(UserContext userContext) { CONTEXT.set(userContext); }
    public static void clear() { CONTEXT.remove(); }

    public String getEmail() { return email; }
    public List<String> getRoles() { return roles; }
}
