package com.turkcell.library.application.features.loan.command.create;

import com.turkcell.library.core.mediator.cqrs.RequestHandler;
import org.springframework.stereotype.Component;
import com.turkcell.library.domain.entity.Loan;
import com.turkcell.library.persistence.repository.LoanRepository;
import com.turkcell.library.domain.entity.Book;
import com.turkcell.library.persistence.repository.BookRepository;
import com.turkcell.library.domain.entity.Member;
import com.turkcell.library.persistence.repository.MemberRepository;
import com.turkcell.library.domain.entity.Librarian;
import com.turkcell.library.persistence.repository.LibrarianRepository;
import com.turkcell.library.exception.type.NotFoundException;

@Component
public class CreateLoanCommandHandler implements RequestHandler<CreateLoanCommand, CreatedLoanResponse> {
    private final LoanRepository repository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final LibrarianRepository librarianRepository;
    
    public CreateLoanCommandHandler(LoanRepository repository, BookRepository bookRepository, MemberRepository memberRepository, LibrarianRepository librarianRepository) {
        this.repository = repository;
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
        this.librarianRepository = librarianRepository;
    }

    @Override
    public CreatedLoanResponse handle(CreateLoanCommand request) {
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
