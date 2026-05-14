package com.turkcell.library.core.mediator;

import com.turkcell.library.core.mediator.cqrs.Request;

public interface Mediator {
    <R, T extends Request<R>> R send(T request);
}
