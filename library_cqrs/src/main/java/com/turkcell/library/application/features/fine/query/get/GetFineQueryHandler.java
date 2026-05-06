package com.turkcell.library.application.features.fine.query.get;

import com.turkcell.library.core.mediator.cqrs.RequestHandler;
import org.springframework.stereotype.Component;
import com.turkcell.library.domain.entity.Fine;
import com.turkcell.library.persistence.repository.FineRepository;
import com.turkcell.library.exception.type.NotFoundException;

@Component
public class GetFineQueryHandler implements RequestHandler<GetFineQuery, GetFineResponse> {
    private final FineRepository repository;
    
    public GetFineQueryHandler(FineRepository repository) {
        this.repository = repository;
    }

    @Override
    public GetFineResponse handle(GetFineQuery request) {
        Integer id = request.getId();
        Fine entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Fine not found"));
        GetFineResponse response = new GetFineResponse();
        response.setId(entity.getId());
        if (entity.getMember() != null) {
            response.setMemberId(entity.getMember().getId());
        }
        if (entity.getLoan() != null) {
            response.setLoanId(entity.getLoan().getId());
        }
        response.setFineDate(entity.getFineDate());
        response.setFineAmount(entity.getFineAmount());
        if (entity.getLibrarian() != null) {
            response.setLibrarianId(entity.getLibrarian().getId());
        }
        return response;
    }
}
