package com.turkcell.library.web.controller;

import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import java.util.List;

import com.turkcell.library.core.mediator.Mediator;
import com.turkcell.library.application.features.author.command.create.CreateAuthorCommand;
import com.turkcell.library.application.features.author.command.create.CreatedAuthorResponse;
import com.turkcell.library.application.features.author.command.update.UpdateAuthorCommand;
import com.turkcell.library.application.features.author.command.update.UpdatedAuthorResponse;
import com.turkcell.library.application.features.author.command.delete.DeleteAuthorCommand;
import com.turkcell.library.application.features.author.query.get.GetAuthorQuery;
import com.turkcell.library.application.features.author.query.get.GetAuthorResponse;
import com.turkcell.library.application.features.author.query.list.ListAuthorQuery;
import com.turkcell.library.application.features.author.query.list.ListAuthorResponse;

@RestController
@RequestMapping("/api/authors")
public class AuthorsController {

    private final Mediator mediator;

    public AuthorsController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedAuthorResponse create(@Valid @RequestBody CreateAuthorCommand command) {
        return mediator.send(command);
    }

    @GetMapping
    public List<ListAuthorResponse> getAll() {
        return mediator.send(new ListAuthorQuery());
    }

    @GetMapping("/{id}")
    public GetAuthorResponse getById(@PathVariable Integer id) {
        return mediator.send(new GetAuthorQuery(id));
    }

    @PutMapping("/{id}")
    public UpdatedAuthorResponse update(@PathVariable Integer id, @Valid @RequestBody UpdateAuthorCommand command) {
        command.setId(id);
        return mediator.send(command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        mediator.send(new DeleteAuthorCommand(id));
    }
}
