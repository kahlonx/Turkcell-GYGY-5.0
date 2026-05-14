package com.turkcell.library.application.features.finepayment.query.get;

import com.turkcell.library.core.mediator.cqrs.RequestHandler;
import org.springframework.stereotype.Component;
import com.turkcell.library.domain.entity.FinePayment;
import com.turkcell.library.persistence.repository.FinePaymentRepository;
import com.turkcell.library.persistence.repository.MemberRepository;
import com.turkcell.library.exception.type.NotFoundException;

@Component
public class GetFinePaymentQueryHandler implements RequestHandler<GetFinePaymentQuery, GetFinePaymentResponse> {
    private final FinePaymentRepository repository;
    private final MemberRepository memberRepository;

    public GetFinePaymentQueryHandler(FinePaymentRepository repository, MemberRepository memberRepository) {
        this.repository = repository;
        this.memberRepository = memberRepository;
    }

    @Override
    public GetFinePaymentResponse handle(GetFinePaymentQuery request) {
        Integer id = request.getId();
        FinePayment entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Fine payment not found"));
        GetFinePaymentResponse response = new GetFinePaymentResponse();
        response.setId(entity.getId());
        if (entity.getMember() != null) {
            response.setMemberId(entity.getMember().getId());
        }
        response.setPaymentAmount(entity.getPaymentAmount());
        response.setPaymentDate(entity.getPaymentDate());

        if (entity.getMember() != null) {
            response.setMemberId(entity.getMember().getId());
        }
        response.setPaymentAmount(entity.getPaymentAmount());
        response.setPaymentDate(entity.getPaymentDate());

        return response;
    }
}
