package com.turkcell.library.application.features.finepayment.query.list;

import com.turkcell.library.core.mediator.cqrs.RequestHandler;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.ArrayList;
import com.turkcell.library.domain.entity.FinePayment;
import com.turkcell.library.persistence.repository.FinePaymentRepository;


@Component
public class ListFinePaymentQueryHandler
        implements RequestHandler<ListFinePaymentQuery, List<ListFinePaymentResponse>> {
    private final FinePaymentRepository repository;

    public ListFinePaymentQueryHandler(FinePaymentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ListFinePaymentResponse> handle(ListFinePaymentQuery request) {
        List<FinePayment> entities = repository.findAll();
        List<ListFinePaymentResponse> responses = new ArrayList<>();
        for (FinePayment entity : entities) {
            ListFinePaymentResponse response = new ListFinePaymentResponse();
            response.setId(entity.getId());
            if (entity.getMember() != null) {
                response.setMemberId(entity.getMember().getId());
            }
            response.setPaymentAmount(entity.getPaymentAmount());
            response.setPaymentDate(entity.getPaymentDate());

            responses.add(response);
        }
        return responses;
    }
}
