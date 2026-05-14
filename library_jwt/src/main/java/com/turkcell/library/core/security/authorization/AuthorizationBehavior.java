package com.turkcell.library.core.security.authorization;

import com.turkcell.library.core.mediator.cqrs.Request;
import com.turkcell.library.core.mediator.pipeline.PipelineBehavior;
import com.turkcell.library.core.mediator.pipeline.RequestHandlerDelegate;
import com.turkcell.library.core.security.context.UserContext;
import com.turkcell.library.exception.type.AuthenticatedException;
import com.turkcell.library.exception.type.AuthorizationException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(0)
public class AuthorizationBehavior implements PipelineBehavior {

    @Override
    public <R, T extends Request<R>> R handle(T request, RequestHandlerDelegate<R> next) {
        if (!(request instanceof AuthorizableRequest authorizableRequest)) {
            return next.handle();
        }

        UserContext userContext = UserContext.getCurrentUser();
        if (userContext == null) {
            throw new AuthenticatedException("Authentication is required to access this resource.");
        }

        List<String> requiredRoles = authorizableRequest.getRequiredRoles();
        if (requiredRoles != null && !requiredRoles.isEmpty()) {
            boolean hasRole = requiredRoles.stream()
                    .anyMatch(role -> userContext.getRoles().contains(role));
            if (!hasRole) {
                throw new AuthorizationException("Insufficient permissions to access this resource.");
            }
        }

        return next.handle();
    }
}
