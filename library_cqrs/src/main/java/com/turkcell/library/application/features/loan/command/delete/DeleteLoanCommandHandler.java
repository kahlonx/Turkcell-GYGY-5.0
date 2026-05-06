package com.turkcell.library.application.features.loan.command.delete;

import com.turkcell.library.core.mediator.cqrs.RequestHandler;
import org.springframework.stereotype.Component;
import com.turkcell.library.persistence.repository.LoanRepository;

@Component
public class DeleteLoanCommandHandler implements RequestHandler<DeleteLoanCommand, Void> {
    private final LoanRepository repository;

    public DeleteLoanCommandHandler(LoanRepository repository) {
        this.repository = repository;
    }

    @Override
    public Void handle(DeleteLoanCommand request) {
        Integer id = request.getId();
        repository.deleteById(id);
        return null;
    }
}
