package com.turkcell.library.application.features.member.command.update;

import com.turkcell.library.core.mediator.cqrs.RequestHandler;
import org.springframework.stereotype.Component;
import com.turkcell.library.domain.entity.Member;
import com.turkcell.library.persistence.repository.MemberRepository;
import com.turkcell.library.domain.entity.MemberStatus;
import com.turkcell.library.persistence.repository.MemberStatusRepository;
import com.turkcell.library.exception.type.NotFoundException;

@Component
public class UpdateMemberCommandHandler implements RequestHandler<UpdateMemberCommand, UpdatedMemberResponse> {
    private final MemberRepository repository;
    private final MemberStatusRepository memberstatusRepository;

    public UpdateMemberCommandHandler(MemberRepository repository, MemberStatusRepository memberstatusRepository) {
        this.repository = repository;
        this.memberstatusRepository = memberstatusRepository;
    }

    @Override
    public UpdatedMemberResponse handle(UpdateMemberCommand request) {
        Integer id = request.getId();
        Member entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Member not found"));
        if (request.getStatusId() != null) {
            MemberStatus memberstatus = memberstatusRepository.findById(request.getStatusId())
                    .orElseThrow(() -> new NotFoundException("MemberStatus not found"));
            entity.setStatus(memberstatus);
        }
        if (request.getFirstName() != null) {
            entity.setFirstName(request.getFirstName());
        }
        if (request.getLastName() != null) {
            entity.setLastName(request.getLastName());
        }
        if (request.getTckn() != null) {
            entity.setTckn(request.getTckn());
        }
        entity = repository.save(entity);
        UpdatedMemberResponse response = new UpdatedMemberResponse();
        response.setId(entity.getId());
        response.setFirstName(entity.getFirstName());
        response.setLastName(entity.getLastName());
        response.setTckn(entity.getTckn());
        if (entity.getStatus() != null) {
            response.setStatusId(entity.getStatus().getId());
        }

        response.setFirstName(entity.getFirstName());
        response.setLastName(entity.getLastName());
        response.setTckn(entity.getTckn());
        if (entity.getStatus() != null) {
            response.setStatusId(entity.getStatus().getId());
        }

        return response;
    }
}
