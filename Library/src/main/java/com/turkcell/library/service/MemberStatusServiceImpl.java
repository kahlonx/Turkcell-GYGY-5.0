package com.turkcell.library.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import com.turkcell.library.dto.*;
import com.turkcell.library.entity.MemberStatus;
import com.turkcell.library.repository.MemberStatusRepository;
import com.turkcell.library.exception.type.NotFoundException;

@Service
public class MemberStatusServiceImpl implements MemberStatusService {
    private final MemberStatusRepository repository;

    public MemberStatusServiceImpl(MemberStatusRepository repository) {
        this.repository = repository;
    }

    @Override
    public CreatedMemberStatusResponse create(CreateMemberStatusRequest request) {
        MemberStatus entity = new MemberStatus();
        entity.setStatusValue(request.getStatusValue());
        entity = repository.save(entity);
        CreatedMemberStatusResponse response = new CreatedMemberStatusResponse();
        response.setId(entity.getId());
        return response;
    }

    @Override
    public List<ListMemberStatusResponse> getAll() {
        List<MemberStatus> entities = repository.findAll();
        List<ListMemberStatusResponse> responses = new ArrayList<>();
        for (MemberStatus entity : entities) {
            ListMemberStatusResponse response = new ListMemberStatusResponse();
            response.setId(entity.getId());
            responses.add(response);
        }
        return responses;
    }

    @Override
    public GetMemberStatusResponse getById(Integer id) {
        MemberStatus entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Member status not found"));
        GetMemberStatusResponse response = new GetMemberStatusResponse();
        response.setId(entity.getId());
        return response;
    }

    @Override
    public UpdatedMemberStatusResponse update(Integer id, UpdateMemberStatusRequest request) {
        MemberStatus entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Member status not found"));
        if (request.getStatusValue() != null) {
            entity.setStatusValue(request.getStatusValue());
        }
        entity = repository.save(entity);
        UpdatedMemberStatusResponse response = new UpdatedMemberStatusResponse();
        response.setId(entity.getId());
        return response;
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}