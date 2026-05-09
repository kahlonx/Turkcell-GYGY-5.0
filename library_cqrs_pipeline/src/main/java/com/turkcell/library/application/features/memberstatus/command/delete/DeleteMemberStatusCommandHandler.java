package com.turkcell.library.application.features.memberstatus.command.delete;

import com.turkcell.library.core.mediator.cqrs.RequestHandler;
import org.springframework.stereotype.Component;
import com.turkcell.library.persistence.repository.MemberStatusRepository;

@Component
public class DeleteMemberStatusCommandHandler implements RequestHandler<DeleteMemberStatusCommand, Void> {
    private final MemberStatusRepository repository;
    
    public DeleteMemberStatusCommandHandler(MemberStatusRepository repository) {
        this.repository = repository;
    }

    @Override
    public Void handle(DeleteMemberStatusCommand request) {
        Integer id = request.getId();
        repository.deleteById(id);
        return null;
    }
}
