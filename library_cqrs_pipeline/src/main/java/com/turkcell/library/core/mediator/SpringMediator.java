package com.turkcell.library.core.mediator;

import com.turkcell.library.core.mediator.cqrs.Request;
import com.turkcell.library.core.mediator.cqrs.RequestHandler;
import com.turkcell.library.core.mediator.pipeline.PipelineBehavior;
import com.turkcell.library.core.mediator.pipeline.RequestHandlerDelegate;
import org.springframework.context.ApplicationContext;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SpringMediator implements Mediator {

    private final ApplicationContext applicationContext;
    private final Map<Class<?>, RequestHandler<?, ?>> handlerMap = new HashMap<>();
    private final List<PipelineBehavior> pipelineBehaviors;

    public SpringMediator(ApplicationContext applicationContext, List<PipelineBehavior> pipelineBehaviors) {
        this.applicationContext = applicationContext;
        this.pipelineBehaviors = pipelineBehaviors;
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

        // The final link in the chain is the actual handler
        RequestHandlerDelegate<R> delegate = () -> handler.handle(request);

        // Chain behaviors from the outside in (reverse order wrapping)
        for (int i = pipelineBehaviors.size() - 1; i >= 0; i--) {
            final PipelineBehavior behavior = pipelineBehaviors.get(i);
            final RequestHandlerDelegate<R> next = delegate;

            // Wrap the current delegate with the behavior
            delegate = () -> behavior.handle(request, next);
        }

        // Execute the pipeline
        return delegate.handle();
    }
}