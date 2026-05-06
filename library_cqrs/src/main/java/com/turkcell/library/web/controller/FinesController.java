package com.turkcell.library.web.controller;

import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import java.util.List;

import com.turkcell.library.core.mediator.Mediator;
import com.turkcell.library.application.features.fine.command.create.CreateFineCommand;
import com.turkcell.library.application.features.fine.command.create.CreatedFineResponse;
import com.turkcell.library.application.features.fine.command.update.UpdateFineCommand;
import com.turkcell.library.application.features.fine.command.update.UpdatedFineResponse;
import com.turkcell.library.application.features.fine.command.delete.DeleteFineCommand;
import com.turkcell.library.application.features.fine.query.get.GetFineQuery;
import com.turkcell.library.application.features.fine.query.get.GetFineResponse;
import com.turkcell.library.application.features.fine.query.list.ListFineQuery;
import com.turkcell.library.application.features.fine.query.list.ListFineResponse;

@RestController
@RequestMapping("/api/fines")
public class FinesController {

    private final Mediator mediator;

    public FinesController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedFineResponse create(@Valid @RequestBody CreateFineCommand command) {
        return mediator.send(command);
    }

    @GetMapping
    public List<ListFineResponse> getAll() {
        return mediator.send(new ListFineQuery());
    }

    @GetMapping("/{id}")
    public GetFineResponse getById(@PathVariable Integer id) {
        return mediator.send(new GetFineQuery(id));
    }

    @PutMapping("/{id}")
    public UpdatedFineResponse update(@PathVariable Integer id, @Valid @RequestBody UpdateFineCommand command) {
        command.setId(id);
        return mediator.send(command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        mediator.send(new DeleteFineCommand(id));
    }
}
