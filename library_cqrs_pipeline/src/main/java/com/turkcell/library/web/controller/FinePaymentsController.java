package com.turkcell.library.web.controller;

import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import java.util.List;

import com.turkcell.library.core.mediator.Mediator;
import com.turkcell.library.application.features.finepayment.command.create.CreateFinePaymentCommand;
import com.turkcell.library.application.features.finepayment.command.create.CreatedFinePaymentResponse;
import com.turkcell.library.application.features.finepayment.command.update.UpdateFinePaymentCommand;
import com.turkcell.library.application.features.finepayment.command.update.UpdatedFinePaymentResponse;
import com.turkcell.library.application.features.finepayment.command.delete.DeleteFinePaymentCommand;
import com.turkcell.library.application.features.finepayment.query.get.GetFinePaymentQuery;
import com.turkcell.library.application.features.finepayment.query.get.GetFinePaymentResponse;
import com.turkcell.library.application.features.finepayment.query.list.ListFinePaymentQuery;
import com.turkcell.library.application.features.finepayment.query.list.ListFinePaymentResponse;

@RestController
@RequestMapping("/api/fine-payments")
public class FinePaymentsController {

    private final Mediator mediator;

    public FinePaymentsController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedFinePaymentResponse create(@Valid @RequestBody CreateFinePaymentCommand command) {
        return mediator.send(command);
    }

    @GetMapping
    public List<ListFinePaymentResponse> getAll() {
        return mediator.send(new ListFinePaymentQuery());
    }

    @GetMapping("/{id}")
    public GetFinePaymentResponse getById(@PathVariable Integer id) {
        return mediator.send(new GetFinePaymentQuery(id));
    }

    @PutMapping("/{id}")
    public UpdatedFinePaymentResponse update(@PathVariable Integer id, @Valid @RequestBody UpdateFinePaymentCommand command) {
        command.setId(id);
        return mediator.send(command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        mediator.send(new DeleteFinePaymentCommand(id));
    }
}
