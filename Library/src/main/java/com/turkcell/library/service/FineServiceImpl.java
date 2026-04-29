package com.turkcell.library.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import com.turkcell.library.dto.*;
import com.turkcell.library.entity.Fine;
import com.turkcell.library.repository.FineRepository;
import com.turkcell.library.entity.Member;
import com.turkcell.library.repository.MemberRepository;
import com.turkcell.library.entity.Loan;
import com.turkcell.library.repository.LoanRepository;
import com.turkcell.library.entity.Librarian;
import com.turkcell.library.repository.LibrarianRepository;
import com.turkcell.library.exception.type.NotFoundException;

@Service
public class FineServiceImpl implements FineService {
    private final FineRepository repository;
    private final MemberRepository memberRepository;
    private final LoanRepository loanRepository;
    private final LibrarianRepository librarianRepository;

    public FineServiceImpl(FineRepository repository, MemberRepository memberRepository, LoanRepository loanRepository, LibrarianRepository librarianRepository) {
        this.repository = repository;
        this.memberRepository = memberRepository;
        this.loanRepository = loanRepository;
        this.librarianRepository = librarianRepository;
    }

    @Override
    public CreatedFineResponse create(CreateFineRequest request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new NotFoundException("Member not found"));
        Loan loan = loanRepository.findById(request.getLoanId())
                .orElseThrow(() -> new NotFoundException("Loan not found"));
        Librarian librarian = librarianRepository.findById(request.getLibrarianId())
                .orElseThrow(() -> new NotFoundException("Librarian not found"));
        
        Fine entity = new Fine();
        entity.setFineDate(request.getFineDate());
        entity.setFineAmount(request.getFineAmount());
        entity.setMember(member);
        entity.setLoan(loan);
        entity.setLibrarian(librarian);
        entity = repository.save(entity);
        CreatedFineResponse response = new CreatedFineResponse();
        response.setId(entity.getId());
        return response;
    }

    @Override
    public List<ListFineResponse> getAll() {
        List<Fine> entities = repository.findAll();
        List<ListFineResponse> responses = new ArrayList<>();
        for (Fine entity : entities) {
            ListFineResponse response = new ListFineResponse();
            response.setId(entity.getId());
            responses.add(response);
        }
        return responses;
    }

    @Override
    public GetFineResponse getById(Integer id) {
        Fine entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Fine not found"));
        GetFineResponse response = new GetFineResponse();
        response.setId(entity.getId());
        return response;
    }

    @Override
    public UpdatedFineResponse update(Integer id, UpdateFineRequest request) {
        Fine entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Fine not found"));
        if (request.getFineAmount() != null) {
            entity.setFineAmount(request.getFineAmount());
        }
        entity = repository.save(entity);
        UpdatedFineResponse response = new UpdatedFineResponse();
        response.setId(entity.getId());
        return response;
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}