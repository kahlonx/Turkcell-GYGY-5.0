package com.turkcell.library.application.features.finepayment.command.delete;

import com.turkcell.library.core.mediator.cqrs.RequestHandler;
import org.springframework.stereotype.Component;
import com.turkcell.library.persistence.repository.FinePaymentRepository;

@Component
public class DeleteFinePaymentCommandHandler implements RequestHandler<DeleteFinePaymentCommand, Void> {
    private final FinePaymentRepository repository;
    
    public DeleteFinePaymentCommandHandler(FinePaymentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Void handle(DeleteFinePaymentCommand request) {
        Integer id = request.getId();
        repository.deleteById(id);
        return null;
    }
}
