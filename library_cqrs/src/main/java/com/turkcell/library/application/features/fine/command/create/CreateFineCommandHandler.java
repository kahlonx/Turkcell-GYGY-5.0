package com.turkcell.library.application.features.fine.command.create;

import com.turkcell.library.core.mediator.cqrs.RequestHandler;
import org.springframework.stereotype.Component;
import com.turkcell.library.domain.entity.Fine;
import com.turkcell.library.persistence.repository.FineRepository;
import com.turkcell.library.domain.entity.Member;
import com.turkcell.library.persistence.repository.MemberRepository;
import com.turkcell.library.domain.entity.Loan;
import com.turkcell.library.persistence.repository.LoanRepository;
import com.turkcell.library.domain.entity.Librarian;
import com.turkcell.library.persistence.repository.LibrarianRepository;
import com.turkcell.library.exception.type.NotFoundException;

@Component
public class CreateFineCommandHandler implements RequestHandler<CreateFineCommand, CreatedFineResponse> {
    private final FineRepository repository;
    private final MemberRepository memberRepository;
    private final LoanRepository loanRepository;
    private final LibrarianRepository librarianRepository;
    
    public CreateFineCommandHandler(FineRepository repository, MemberRepository memberRepository, LoanRepository loanRepository, LibrarianRepository librarianRepository) {
        this.repository = repository;
        this.memberRepository = memberRepository;
        this.loanRepository = loanRepository;
        this.librarianRepository = librarianRepository;
    }

    @Override
    public CreatedFineResponse handle(CreateFineCommand request) {
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
