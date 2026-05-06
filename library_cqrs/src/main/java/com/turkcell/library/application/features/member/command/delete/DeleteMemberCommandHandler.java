package com.turkcell.library.application.features.member.command.delete;

import com.turkcell.library.core.mediator.cqrs.RequestHandler;
import org.springframework.stereotype.Component;
import com.turkcell.library.persistence.repository.MemberRepository;

@Component
public class DeleteMemberCommandHandler implements RequestHandler<DeleteMemberCommand, Void> {
    private final MemberRepository repository;

    public DeleteMemberCommandHandler(MemberRepository repository) {
        this.repository = repository;
    }

    @Override
    public Void handle(DeleteMemberCommand request) {
        Integer id = request.getId();
        repository.deleteById(id);
        return null;
    }
}
