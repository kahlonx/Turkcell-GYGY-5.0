package com.turkcell.library.application.features.loan.command.update;

import com.turkcell.library.core.mediator.cqrs.RequestHandler;
import org.springframework.stereotype.Component;
import com.turkcell.library.domain.entity.Loan;
import com.turkcell.library.persistence.repository.LoanRepository;
import com.turkcell.library.persistence.repository.BookRepository;
import com.turkcell.library.persistence.repository.MemberRepository;
import com.turkcell.library.persistence.repository.LibrarianRepository;
import com.turkcell.library.exception.type.NotFoundException;

@Component
public class UpdateLoanCommandHandler implements RequestHandler<UpdateLoanCommand, UpdatedLoanResponse> {
    private final LoanRepository repository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final LibrarianRepository librarianRepository;

    public UpdateLoanCommandHandler(LoanRepository repository, BookRepository bookRepository,
            MemberRepository memberRepository, LibrarianRepository librarianRepository) {
        this.repository = repository;
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
        this.librarianRepository = librarianRepository;
    }

    @Override
    public UpdatedLoanResponse handle(UpdateLoanCommand request) {
        Integer id = request.getId();
        Loan entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Loan not found"));
        if (request.getReturnDate() != null) {
            entity.setReturnDate(request.getReturnDate());
        }
        entity = repository.save(entity);
        UpdatedLoanResponse response = new UpdatedLoanResponse();
        response.setId(entity.getId());
        if (entity.getBook() != null) {
            response.setBookId(entity.getBook().getId());
        }
        if (entity.getMember() != null) {
            response.setMemberId(entity.getMember().getId());
        }
        response.setLoanDate(entity.getLoanDate());
        response.setReturnDate(entity.getReturnDate());
        if (entity.getLibrarian() != null) {
            response.setLibrarianId(entity.getLibrarian().getId());
        }

        if (entity.getBook() != null) {
            response.setBookId(entity.getBook().getId());
        }
        if (entity.getMember() != null) {
            response.setMemberId(entity.getMember().getId());
        }
        response.setLoanDate(entity.getLoanDate());
        response.setReturnDate(entity.getReturnDate());
        if (entity.getLibrarian() != null) {
            response.setLibrarianId(entity.getLibrarian().getId());
        }

        return response;
    }
}
