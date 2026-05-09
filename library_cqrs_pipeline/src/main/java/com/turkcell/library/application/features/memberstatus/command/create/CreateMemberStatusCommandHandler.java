package com.turkcell.library.application.features.memberstatus.command.create;

import com.turkcell.library.core.mediator.cqrs.RequestHandler;
import org.springframework.stereotype.Component;
import com.turkcell.library.domain.entity.MemberStatus;
import com.turkcell.library.persistence.repository.MemberStatusRepository;

@Component
public class CreateMemberStatusCommandHandler implements RequestHandler<CreateMemberStatusCommand, CreatedMemberStatusResponse> {
    private final MemberStatusRepository repository;
    
    public CreateMemberStatusCommandHandler(MemberStatusRepository repository) {
        this.repository = repository;
    }

    @Override
    public CreatedMemberStatusResponse handle(CreateMemberStatusCommand request) {
        MemberStatus entity = new MemberStatus();
        entity.setStatusValue(request.getStatusValue());
        entity = repository.save(entity);
        CreatedMemberStatusResponse response = new CreatedMemberStatusResponse();
        response.setId(entity.getId());
        response.setStatusValue(entity.getStatusValue());
        return response;
    }
}
