package com.turkcell.library.core.mediator.pipeline;

@FunctionalInterface
public interface RequestHandlerDelegate<R> {
    R handle();
}