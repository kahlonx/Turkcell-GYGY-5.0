package com.turkcell.library.application.features.memberstatus.query.list;

import com.turkcell.library.core.mediator.cqrs.RequestHandler;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.ArrayList;
import com.turkcell.library.domain.entity.MemberStatus;
import com.turkcell.library.persistence.repository.MemberStatusRepository;

@Component
public class ListMemberStatusQueryHandler implements RequestHandler<ListMemberStatusQuery, List<ListMemberStatusResponse>> {
    private final MemberStatusRepository repository;
    
    public ListMemberStatusQueryHandler(MemberStatusRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ListMemberStatusResponse> handle(ListMemberStatusQuery request) {
        List<MemberStatus> entities = repository.findAll();
        List<ListMemberStatusResponse> responses = new ArrayList<>();
        for (MemberStatus entity : entities) {
            ListMemberStatusResponse response = new ListMemberStatusResponse();
            response.setId(entity.getId());
            response.setStatusValue(entity.getStatusValue());

            responses.add(response);
        }
        return responses;
    }
}
