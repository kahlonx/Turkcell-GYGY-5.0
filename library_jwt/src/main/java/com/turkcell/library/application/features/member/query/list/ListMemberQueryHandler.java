package com.turkcell.library.application.features.member.query.list;

import com.turkcell.library.core.mediator.cqrs.RequestHandler;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.ArrayList;
import com.turkcell.library.domain.entity.Member;
import com.turkcell.library.persistence.repository.MemberRepository;

@Component
public class ListMemberQueryHandler implements RequestHandler<ListMemberQuery, List<ListMemberResponse>> {
    private final MemberRepository repository;
    
    public ListMemberQueryHandler(MemberRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ListMemberResponse> handle(ListMemberQuery request) {
        List<Member> entities = repository.findAll();
        List<ListMemberResponse> responses = new ArrayList<>();
        for (Member entity : entities) {
            ListMemberResponse response = new ListMemberResponse();
            response.setId(entity.getId());
            response.setFirstName(entity.getFirstName());
            response.setLastName(entity.getLastName());
            response.setTckn(entity.getTckn());
            if (entity.getStatus() != null) {
                response.setStatusId(entity.getStatus().getId());
            }

            responses.add(response);
        }
        return responses;
    }
}
