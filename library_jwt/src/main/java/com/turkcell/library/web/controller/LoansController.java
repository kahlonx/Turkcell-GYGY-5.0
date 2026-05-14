package com.turkcell.library.web.controller;

import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import java.util.List;

import com.turkcell.library.core.mediator.Mediator;
import com.turkcell.library.application.features.loan.command.create.CreateLoanCommand;
import com.turkcell.library.application.features.loan.command.create.CreatedLoanResponse;
import com.turkcell.library.application.features.loan.command.update.UpdateLoanCommand;
import com.turkcell.library.application.features.loan.command.update.UpdatedLoanResponse;
import com.turkcell.library.application.features.loan.command.delete.DeleteLoanCommand;
import com.turkcell.library.application.features.loan.query.get.GetLoanQuery;
import com.turkcell.library.application.features.loan.query.get.GetLoanResponse;
import com.turkcell.library.application.features.loan.query.list.ListLoanQuery;
import com.turkcell.library.application.features.loan.query.list.ListLoanResponse;

@RestController
@RequestMapping("/api/loans")
public class LoansController {

    private final Mediator mediator;

    public LoansController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedLoanResponse create(@Valid @RequestBody CreateLoanCommand command) {
        return mediator.send(command);
    }

    @GetMapping
    public List<ListLoanResponse> getAll() {
        return mediator.send(new ListLoanQuery());
    }

    @GetMapping("/{id}")
    public GetLoanResponse getById(@PathVariable Integer id) {
        return mediator.send(new GetLoanQuery(id));
    }

    @PutMapping("/{id}")
    public UpdatedLoanResponse update(@PathVariable Integer id, @Valid @RequestBody UpdateLoanCommand command) {
        command.setId(id);
        return mediator.send(command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        mediator.send(new DeleteLoanCommand(id));
    }
}
