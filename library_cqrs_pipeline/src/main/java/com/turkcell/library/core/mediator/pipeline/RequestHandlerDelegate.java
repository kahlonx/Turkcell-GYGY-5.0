package com.turkcell.library.core.mediator.pipeline;

/**
 * Represents the next operation in the pipeline.
 */
@FunctionalInterface
public interface RequestHandlerDelegate<R> {
    R handle();
}