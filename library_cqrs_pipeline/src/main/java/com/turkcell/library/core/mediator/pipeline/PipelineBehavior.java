package com.turkcell.library.core.mediator.pipeline;

import com.turkcell.library.core.mediator.cqrs.Request;

/**
 * Pipeline behavior to surround the inner handler.
 * Implementations are executed in the order they are evaluated by Spring.
 */
public interface PipelineBehavior {
    <R, T extends Request<R>> R handle(T request, RequestHandlerDelegate<R> next);
}