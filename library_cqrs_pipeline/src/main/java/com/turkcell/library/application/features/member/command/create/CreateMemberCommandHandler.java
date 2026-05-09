package com.turkcell.library.application.features.member.command.create;

import com.turkcell.library.core.mediator.cqrs.RequestHandler;
import org.springframework.stereotype.Component;
import com.turkcell.library.domain.entity.Member;
import com.turkcell.library.persistence.repository.MemberRepository;
import com.turkcell.library.domain.entity.MemberStatus;
import com.turkcell.library.persistence.repository.MemberStatusRepository;
import com.turkcell.library.exception.type.NotFoundException;

@Component
public class CreateMemberCommandHandler implements RequestHandler<CreateMemberCommand, CreatedMemberResponse> {
    private final MemberRepository repository;
    private final MemberStatusRepository memberstatusRepository;
    
    public CreateMemberCommandHandler(MemberRepository repository, MemberStatusRepository memberstatusRepository) {
        this.repository = repository;
        this.memberstatusRepository = memberstatusRepository;
    }

    @Override
    public CreatedMemberResponse handle(CreateMemberCommand request) {
        MemberStatus memberstatus = memberstatusRepository.findById(request.getStatusId())
                .orElseThrow(() -> new NotFoundException("MemberStatus not found"));
        Member entity = new Member();
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setTckn(request.getTckn());
        entity.setStatus(memberstatus);
        entity = repository.save(entity);
        CreatedMemberResponse response = new CreatedMemberResponse();
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
