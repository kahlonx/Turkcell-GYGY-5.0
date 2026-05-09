package com.turkcell.library.core.mediator.cqrs;

public interface RequestHandler<T extends Request<R>, R> {
    R handle(T request);
}
