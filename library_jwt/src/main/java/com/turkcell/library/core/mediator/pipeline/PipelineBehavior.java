package com.turkcell.library.core.mediator.pipeline;

import com.turkcell.library.core.mediator.cqrs.Request;

public interface PipelineBehavior {
    <R, T extends Request<R>> R handle(T request, RequestHandlerDelegate<R> next);
}