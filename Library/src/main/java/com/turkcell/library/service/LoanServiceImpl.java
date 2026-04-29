package com.turkcell.library.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import com.turkcell.library.dto.*;
import com.turkcell.library.entity.Loan;
import com.turkcell.library.repository.LoanRepository;
import com.turkcell.library.entity.Book;
import com.turkcell.library.repository.BookRepository;
import com.turkcell.library.entity.Member;
import com.turkcell.library.repository.MemberRepository;
import com.turkcell.library.entity.Librarian;
import com.turkcell.library.repository.LibrarianRepository;
import com.turkcell.library.exception.type.NotFoundException;

@Service
public class LoanServiceImpl implements LoanService {
    private final LoanRepository repository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final LibrarianRepository librarianRepository;

    public LoanServiceImpl(LoanRepository repository, BookRepository bookRepository, MemberRepository memberRepository, LibrarianRepository librarianRepository) {
        this.repository = repository;
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
        this.librarianRepository = librarianRepository;
    }

    @Override
    public CreatedLoanResponse create(CreateLoanRequest request) {
        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new NotFoundException("Book not found"));
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new NotFoundException("Member not found"));
        Librarian librarian = librarianRepository.findById(request.getLibrarianId())
                .orElseThrow(() -> new NotFoundException("Librarian not found"));
        
        Loan entity = new Loan();
        entity.setLoanDate(request.getLoanDate());
        entity.setBook(book);
        entity.setMember(member);
        entity.setLibrarian(librarian);
        entity = repository.save(entity);
        CreatedLoanResponse response = new CreatedLoanResponse();
        response.setId(entity.getId());
        return response;
    }

    @Override
    public List<ListLoanResponse> getAll() {
        List<Loan> entities = repository.findAll();
        List<ListLoanResponse> responses = new ArrayList<>();
        for (Loan entity : entities) {
            ListLoanResponse response = new ListLoanResponse();
            response.setId(entity.getId());
            responses.add(response);
        }
        return responses;
    }

    @Override
    public GetLoanResponse getById(Integer id) {
        Loan entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Loan not found"));
        GetLoanResponse response = new GetLoanResponse();
        response.setId(entity.getId());
        return response;
    }

    @Override
    public UpdatedLoanResponse update(Integer id, UpdateLoanRequest request) {
        Loan entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Loan not found"));
        if (request.getReturnDate() != null) {
            entity.setReturnDate(request.getReturnDate());
        }
        entity = repository.save(entity);
        UpdatedLoanResponse response = new UpdatedLoanResponse();
        response.setId(entity.getId());
        return response;
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}