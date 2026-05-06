package com.turkcell.library.application.features.finepayment.command.update;

import com.turkcell.library.core.mediator.cqrs.RequestHandler;
import org.springframework.stereotype.Component;
import com.turkcell.library.domain.entity.FinePayment;
import com.turkcell.library.persistence.repository.FinePaymentRepository;
import com.turkcell.library.exception.type.NotFoundException;

@Component
public class UpdateFinePaymentCommandHandler implements RequestHandler<UpdateFinePaymentCommand, UpdatedFinePaymentResponse> {
    private final FinePaymentRepository repository;
    
    public UpdateFinePaymentCommandHandler(FinePaymentRepository repository) {
        this.repository = repository;
    }

    @Override
    public UpdatedFinePaymentResponse handle(UpdateFinePaymentCommand request) {
        Integer id = request.getId();
        FinePayment entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Fine payment not found"));
        if (request.getPaymentAmount() != null) {
            entity.setPaymentAmount(request.getPaymentAmount());
        }
        entity = repository.save(entity);
        UpdatedFinePaymentResponse response = new UpdatedFinePaymentResponse();
        response.setId(entity.getId());
        if (entity.getMember() != null) {
            response.setMemberId(entity.getMember().getId());
        }
        response.setPaymentAmount(entity.getPaymentAmount());
        response.setPaymentDate(entity.getPaymentDate());
        return response;
    }
}
