package com.turkcell.library.application.features.memberstatus.command.update;

import com.turkcell.library.core.mediator.cqrs.RequestHandler;
import org.springframework.stereotype.Component;
import com.turkcell.library.domain.entity.MemberStatus;
import com.turkcell.library.persistence.repository.MemberStatusRepository;
import com.turkcell.library.exception.type.NotFoundException;

@Component
public class UpdateMemberStatusCommandHandler implements RequestHandler<UpdateMemberStatusCommand, UpdatedMemberStatusResponse> {
    private final MemberStatusRepository repository;
    
    public UpdateMemberStatusCommandHandler(MemberStatusRepository repository) {
        this.repository = repository;
    }

    @Override
    public UpdatedMemberStatusResponse handle(UpdateMemberStatusCommand request) {
        Integer id = request.getId();
        MemberStatus entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Member status not found"));
        if (request.getStatusValue() != null) {
            entity.setStatusValue(request.getStatusValue());
        }
        entity = repository.save(entity);
        UpdatedMemberStatusResponse response = new UpdatedMemberStatusResponse();
        response.setId(entity.getId());
        response.setStatusValue(entity.getStatusValue());
        return response;
    }
}
