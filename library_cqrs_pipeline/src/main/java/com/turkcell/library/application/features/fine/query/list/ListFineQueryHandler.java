package com.turkcell.library.application.features.fine.query.list;

import com.turkcell.library.core.mediator.cqrs.RequestHandler;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.ArrayList;
import com.turkcell.library.domain.entity.Fine;
import com.turkcell.library.persistence.repository.FineRepository;

@Component
public class ListFineQueryHandler implements RequestHandler<ListFineQuery, List<ListFineResponse>> {
    private final FineRepository repository;
    
    public ListFineQueryHandler(FineRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ListFineResponse> handle(ListFineQuery request) {
        List<Fine> entities = repository.findAll();
        List<ListFineResponse> responses = new ArrayList<>();
        for (Fine entity : entities) {
            ListFineResponse response = new ListFineResponse();
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

            responses.add(response);
        }
        return responses;
    }
}
