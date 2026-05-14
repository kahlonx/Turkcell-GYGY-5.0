package com.turkcell.library.web.controller;

import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import java.util.List;

import com.turkcell.library.core.mediator.Mediator;
import com.turkcell.library.application.features.publisher.command.create.CreatePublisherCommand;
import com.turkcell.library.application.features.publisher.command.create.CreatedPublisherResponse;
import com.turkcell.library.application.features.publisher.command.update.UpdatePublisherCommand;
import com.turkcell.library.application.features.publisher.command.update.UpdatedPublisherResponse;
import com.turkcell.library.application.features.publisher.command.delete.DeletePublisherCommand;
import com.turkcell.library.application.features.publisher.query.get.GetPublisherQuery;
import com.turkcell.library.application.features.publisher.query.get.GetPublisherResponse;
import com.turkcell.library.application.features.publisher.query.list.ListPublisherQuery;
import com.turkcell.library.application.features.publisher.query.list.ListPublisherResponse;

@RestController
@RequestMapping("/api/publishers")
public class PublishersController {

    private final Mediator mediator;

    public PublishersController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedPublisherResponse create(@Valid @RequestBody CreatePublisherCommand command) {
        return mediator.send(command);
    }

    @GetMapping
    public List<ListPublisherResponse> getAll() {
        return mediator.send(new ListPublisherQuery());
    }

    @GetMapping("/{id}")
    public GetPublisherResponse getById(@PathVariable Integer id) {
        return mediator.send(new GetPublisherQuery(id));
    }

    @PutMapping("/{id}")
    public UpdatedPublisherResponse update(@PathVariable Integer id, @Valid @RequestBody UpdatePublisherCommand command) {
        command.setId(id);
        return mediator.send(command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        mediator.send(new DeletePublisherCommand(id));
    }
}
