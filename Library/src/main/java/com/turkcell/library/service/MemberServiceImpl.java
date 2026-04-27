package com.turkcell.library.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import com.turkcell.library.dto.*;
import com.turkcell.library.entity.Member;
import com.turkcell.library.repository.MemberRepository;
import com.turkcell.library.entity.MemberStatus;
import com.turkcell.library.repository.MemberStatusRepository;

@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository repository;
    private final MemberStatusRepository memberstatusRepository;

    public MemberServiceImpl(MemberRepository repository, MemberStatusRepository memberstatusRepository) {
        this.repository = repository;
        this.memberstatusRepository = memberstatusRepository;
    }

    @Override
    public CreatedMemberResponse create(CreateMemberRequest request) {
        MemberStatus memberstatus = memberstatusRepository.findById(request.getStatusId()).orElseThrow(() -> new RuntimeException("MemberStatus not found"));
        Member entity = new Member();
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setTckn(request.getTckn());
        entity.setStatus(memberstatus);
        entity = repository.save(entity);
        CreatedMemberResponse response = new CreatedMemberResponse();
        response.setId(entity.getId());
        return response;
    }

    @Override
    public List<ListMemberResponse> getAll() {
        List<Member> entities = repository.findAll();
        List<ListMemberResponse> responses = new ArrayList<>();
        for (Member entity : entities) {
            ListMemberResponse response = new ListMemberResponse();
            response.setId(entity.getId());
            responses.add(response);
        }
        return responses;
    }

    @Override
    public GetMemberResponse getById(Integer id) {
        Member entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Entity not found"));
        GetMemberResponse response = new GetMemberResponse();
        response.setId(entity.getId());
        return response;
    }

    @Override
    public UpdatedMemberResponse update(Integer id, UpdateMemberRequest request) {
        Member entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Entity not found"));
        if (request.getStatusId() != null) {
            MemberStatus memberstatus = memberstatusRepository.findById(request.getStatusId()).orElseThrow(() -> new RuntimeException("MemberStatus not found"));
            entity.setStatus(memberstatus);
        }
        if (request.getFirstName() != null) {
            entity.setFirstName(request.getFirstName());
        }
        if (request.getLastName() != null) {
            entity.setLastName(request.getLastName());
        }
        if (request.getTckn() != null) {
            entity.setTckn(request.getTckn());
        }
        entity = repository.save(entity);
        UpdatedMemberResponse response = new UpdatedMemberResponse();
        response.setId(entity.getId());
        return response;
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
