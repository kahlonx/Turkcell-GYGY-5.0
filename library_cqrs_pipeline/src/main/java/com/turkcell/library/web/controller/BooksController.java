package com.turkcell.library.web.controller;

import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import java.util.List;

import com.turkcell.library.core.mediator.Mediator;
import com.turkcell.library.application.features.book.command.create.CreateBookCommand;
import com.turkcell.library.application.features.book.command.create.CreatedBookResponse;
import com.turkcell.library.application.features.book.command.update.UpdateBookCommand;
import com.turkcell.library.application.features.book.command.update.UpdatedBookResponse;
import com.turkcell.library.application.features.book.command.delete.DeleteBookCommand;
import com.turkcell.library.application.features.book.query.get.GetBookQuery;
import com.turkcell.library.application.features.book.query.get.GetBookResponse;
import com.turkcell.library.application.features.book.query.list.ListBookQuery;
import com.turkcell.library.application.features.book.query.list.ListBookResponse;

@RestController
@RequestMapping("/api/books")
public class BooksController {

    private final Mediator mediator;

    public BooksController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedBookResponse create(@Valid @RequestBody CreateBookCommand command) {
        return mediator.send(command);
    }

    @GetMapping
    public List<ListBookResponse> getAll() {
        return mediator.send(new ListBookQuery());
    }

    @GetMapping("/{id}")
    public GetBookResponse getById(@PathVariable Integer id) {
        return mediator.send(new GetBookQuery(id));
    }

    @PutMapping("/{id}")
    public UpdatedBookResponse update(@PathVariable Integer id, @Valid @RequestBody UpdateBookCommand command) {
        command.setId(id);
        return mediator.send(command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        mediator.send(new DeleteBookCommand(id));
    }
}
