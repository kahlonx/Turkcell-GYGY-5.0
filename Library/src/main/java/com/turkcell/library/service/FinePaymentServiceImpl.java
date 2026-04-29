package com.turkcell.library.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import com.turkcell.library.dto.*;
import com.turkcell.library.entity.FinePayment;
import com.turkcell.library.repository.FinePaymentRepository;
import com.turkcell.library.entity.Member;
import com.turkcell.library.repository.MemberRepository;
import com.turkcell.library.exception.type.NotFoundException;

@Service
public class FinePaymentServiceImpl implements FinePaymentService {
    private final FinePaymentRepository repository;
    private final MemberRepository memberRepository;

    public FinePaymentServiceImpl(FinePaymentRepository repository, MemberRepository memberRepository) {
        this.repository = repository;
        this.memberRepository = memberRepository;
    }

    @Override
    public CreatedFinePaymentResponse create(CreateFinePaymentRequest request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new NotFoundException("Member not found"));
        FinePayment entity = new FinePayment();
        entity.setPaymentAmount(request.getPaymentAmount());
        entity.setMember(member);
        entity = repository.save(entity);
        CreatedFinePaymentResponse response = new CreatedFinePaymentResponse();
        response.setId(entity.getId());
        return response;
    }

    @Override
    public List<ListFinePaymentResponse> getAll() {
        List<FinePayment> entities = repository.findAll();
        List<ListFinePaymentResponse> responses = new ArrayList<>();
        for (FinePayment entity : entities) {
            ListFinePaymentResponse response = new ListFinePaymentResponse();
            response.setId(entity.getId());
            responses.add(response);
        }
        return responses;
    }

    @Override
    public GetFinePaymentResponse getById(Integer id) {
        FinePayment entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Fine payment not found"));
        GetFinePaymentResponse response = new GetFinePaymentResponse();
        response.setId(entity.getId());
        return response;
    }

    @Override
    public UpdatedFinePaymentResponse update(Integer id, UpdateFinePaymentRequest request) {
        FinePayment entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Fine payment not found"));
        if (request.getPaymentAmount() != null) {
            entity.setPaymentAmount(request.getPaymentAmount());
        }
        entity = repository.save(entity);
        UpdatedFinePaymentResponse response = new UpdatedFinePaymentResponse();
        response.setId(entity.getId());
        return response;
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}