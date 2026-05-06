package com.turkcell.library.web.controller;

import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import java.util.List;

import com.turkcell.library.core.mediator.Mediator;
import com.turkcell.library.application.features.librarian.command.create.CreateLibrarianCommand;
import com.turkcell.library.application.features.librarian.command.create.CreatedLibrarianResponse;
import com.turkcell.library.application.features.librarian.command.update.UpdateLibrarianCommand;
import com.turkcell.library.application.features.librarian.command.update.UpdatedLibrarianResponse;
import com.turkcell.library.application.features.librarian.command.delete.DeleteLibrarianCommand;
import com.turkcell.library.application.features.librarian.query.get.GetLibrarianQuery;
import com.turkcell.library.application.features.librarian.query.get.GetLibrarianResponse;
import com.turkcell.library.application.features.librarian.query.list.ListLibrarianQuery;
import com.turkcell.library.application.features.librarian.query.list.ListLibrarianResponse;

@RestController
@RequestMapping("/api/librarians")
public class LibrariansController {

    private final Mediator mediator;

    public LibrariansController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedLibrarianResponse create(@Valid @RequestBody CreateLibrarianCommand command) {
        return mediator.send(command);
    }

    @GetMapping
    public List<ListLibrarianResponse> getAll() {
        return mediator.send(new ListLibrarianQuery());
    }

    @GetMapping("/{id}")
    public GetLibrarianResponse getById(@PathVariable Integer id) {
        return mediator.send(new GetLibrarianQuery(id));
    }

    @PutMapping("/{id}")
    public UpdatedLibrarianResponse update(@PathVariable Integer id, @Valid @RequestBody UpdateLibrarianCommand command) {
        command.setId(id);
        return mediator.send(command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        mediator.send(new DeleteLibrarianCommand(id));
    }
}
