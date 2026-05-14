package com.turkcell.library.application.features.member.query.get;

import com.turkcell.library.core.mediator.cqrs.RequestHandler;
import org.springframework.stereotype.Component;
import com.turkcell.library.domain.entity.Member;
import com.turkcell.library.persistence.repository.MemberRepository;
import com.turkcell.library.exception.type.NotFoundException;

@Component
public class GetMemberQueryHandler implements RequestHandler<GetMemberQuery, GetMemberResponse> {
    private final MemberRepository repository;
    
    public GetMemberQueryHandler(MemberRepository repository) {
        this.repository = repository;
    }

    @Override
    public GetMemberResponse handle(GetMemberQuery request) {
        Integer id = request.getId();
        Member entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Member not found"));
        GetMemberResponse response = new GetMemberResponse();
        response.setId(entity.getId());
        response.setFirstName(entity.getFirstName());
        response.setLastName(entity.getLastName());
        response.setTckn(entity.getTckn());
        if (entity.getStatus() != null) {
            response.setStatusId(entity.getStatus().getId());
        }
        return response;
    }
}
