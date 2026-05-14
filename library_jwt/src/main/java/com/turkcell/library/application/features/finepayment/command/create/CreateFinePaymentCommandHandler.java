package com.turkcell.library.application.features.finepayment.command.create;

import com.turkcell.library.core.mediator.cqrs.RequestHandler;
import org.springframework.stereotype.Component;
import com.turkcell.library.domain.entity.FinePayment;
import com.turkcell.library.persistence.repository.FinePaymentRepository;
import com.turkcell.library.domain.entity.Member;
import com.turkcell.library.persistence.repository.MemberRepository;
import com.turkcell.library.exception.type.NotFoundException;

@Component
public class CreateFinePaymentCommandHandler implements RequestHandler<CreateFinePaymentCommand, CreatedFinePaymentResponse> {
    private final FinePaymentRepository repository;
    private final MemberRepository memberRepository;
    
    public CreateFinePaymentCommandHandler(FinePaymentRepository repository, MemberRepository memberRepository) {
        this.repository = repository;
        this.memberRepository = memberRepository;
    }

    @Override
    public CreatedFinePaymentResponse handle(CreateFinePaymentCommand request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new NotFoundException("Member not found"));
        FinePayment entity = new FinePayment();
        entity.setPaymentAmount(request.getPaymentAmount());
        entity.setMember(member);
        entity = repository.save(entity);
        CreatedFinePaymentResponse response = new CreatedFinePaymentResponse();
        response.setId(entity.getId());
        if (entity.getMember() != null) {
            response.setMemberId(entity.getMember().getId());
        }
        response.setPaymentAmount(entity.getPaymentAmount());
        response.setPaymentDate(entity.getPaymentDate());
        return response;
    }
}
