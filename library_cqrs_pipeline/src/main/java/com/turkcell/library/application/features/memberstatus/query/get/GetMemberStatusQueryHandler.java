package com.turkcell.library.application.features.memberstatus.query.get;

import com.turkcell.library.core.mediator.cqrs.RequestHandler;
import org.springframework.stereotype.Component;
import com.turkcell.library.domain.entity.MemberStatus;
import com.turkcell.library.persistence.repository.MemberStatusRepository;
import com.turkcell.library.exception.type.NotFoundException;

@Component
public class GetMemberStatusQueryHandler implements RequestHandler<GetMemberStatusQuery, GetMemberStatusResponse> {
    private final MemberStatusRepository repository;
    
    public GetMemberStatusQueryHandler(MemberStatusRepository repository) {
        this.repository = repository;
    }

    @Override
    public GetMemberStatusResponse handle(GetMemberStatusQuery request) {
        Integer id = request.getId();
        MemberStatus entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Member status not found"));
        GetMemberStatusResponse response = new GetMemberStatusResponse();
        response.setId(entity.getId());
        response.setStatusValue(entity.getStatusValue());
        return response;
    }
}
