package com.turkcell.library.core.mediator;

import com.turkcell.library.core.mediator.cqrs.Request;
import com.turkcell.library.core.mediator.cqrs.RequestHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SpringMediator implements Mediator {

    private final ApplicationContext applicationContext;
    private final Map<Class<?>, RequestHandler<?, ?>> handlerMap = new HashMap<>();

    public SpringMediator(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        initializeHandlers();
    }

    private void initializeHandlers() {
        String[] beanNames = applicationContext.getBeanNamesForType(RequestHandler.class);
        for (String beanName : beanNames) {
            RequestHandler<?, ?> handler = applicationContext.getBean(beanName, RequestHandler.class);
            Class<?>[] typeArguments = GenericTypeResolver.resolveTypeArguments(handler.getClass(),
                    RequestHandler.class);
            if (typeArguments != null && typeArguments.length > 0) {
                Class<?> requestType = typeArguments[0];
                handlerMap.put(requestType, handler);
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <R, T extends Request<R>> R send(T request) {
        RequestHandler<T, R> handler = (RequestHandler<T, R>) handlerMap.get(request.getClass());
        if (handler == null) {
            throw new IllegalArgumentException("No handler found for request: " + request.getClass().getName());
        }
        return handler.handle(request);
    }
}
