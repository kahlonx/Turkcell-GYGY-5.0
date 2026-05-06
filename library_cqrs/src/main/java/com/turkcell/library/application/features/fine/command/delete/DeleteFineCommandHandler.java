package com.turkcell.library.application.features.fine.command.delete;

import com.turkcell.library.core.mediator.cqrs.RequestHandler;
import org.springframework.stereotype.Component;
import com.turkcell.library.persistence.repository.FineRepository;

@Component
public class DeleteFineCommandHandler implements RequestHandler<DeleteFineCommand, Void> {
    private final FineRepository repository;
    
    public DeleteFineCommandHandler(FineRepository repository) {
        this.repository = repository;
    }

    @Override
    public Void handle(DeleteFineCommand request) {
        Integer id = request.getId();
        repository.deleteById(id);
        return null;
    }
}
