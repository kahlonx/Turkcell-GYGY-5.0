package com.turkcell.library.application.features.loan.query.list;

import com.turkcell.library.core.mediator.cqrs.RequestHandler;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.ArrayList;
import com.turkcell.library.domain.entity.Loan;
import com.turkcell.library.persistence.repository.LoanRepository;
import com.turkcell.library.persistence.repository.BookRepository;
import com.turkcell.library.persistence.repository.MemberRepository;
import com.turkcell.library.persistence.repository.LibrarianRepository;

@Component
public class ListLoanQueryHandler implements RequestHandler<ListLoanQuery, List<ListLoanResponse>> {
    private final LoanRepository repository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final LibrarianRepository librarianRepository;

    public ListLoanQueryHandler(LoanRepository repository, BookRepository bookRepository,
            MemberRepository memberRepository, LibrarianRepository librarianRepository) {
        this.repository = repository;
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
        this.librarianRepository = librarianRepository;
    }

    @Override
    public List<ListLoanResponse> handle(ListLoanQuery request) {
        List<Loan> entities = repository.findAll();
        List<ListLoanResponse> responses = new ArrayList<>();
        for (Loan entity : entities) {
            ListLoanResponse response = new ListLoanResponse();
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

            responses.add(response);
        }
        return responses;
    }
}
