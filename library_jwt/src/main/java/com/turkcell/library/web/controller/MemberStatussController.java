package com.turkcell.library.web.controller;

import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import java.util.List;

import com.turkcell.library.core.mediator.Mediator;
import com.turkcell.library.application.features.memberstatus.command.create.CreateMemberStatusCommand;
import com.turkcell.library.application.features.memberstatus.command.create.CreatedMemberStatusResponse;
import com.turkcell.library.application.features.memberstatus.command.update.UpdateMemberStatusCommand;
import com.turkcell.library.application.features.memberstatus.command.update.UpdatedMemberStatusResponse;
import com.turkcell.library.application.features.memberstatus.command.delete.DeleteMemberStatusCommand;
import com.turkcell.library.application.features.memberstatus.query.get.GetMemberStatusQuery;
import com.turkcell.library.application.features.memberstatus.query.get.GetMemberStatusResponse;
import com.turkcell.library.application.features.memberstatus.query.list.ListMemberStatusQuery;
import com.turkcell.library.application.features.memberstatus.query.list.ListMemberStatusResponse;

@RestController
@RequestMapping("/api/member-statuses")
public class MemberStatussController {

    private final Mediator mediator;

    public MemberStatussController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedMemberStatusResponse create(@Valid @RequestBody CreateMemberStatusCommand command) {
        return mediator.send(command);
    }

    @GetMapping
    public List<ListMemberStatusResponse> getAll() {
        return mediator.send(new ListMemberStatusQuery());
    }

    @GetMapping("/{id}")
    public GetMemberStatusResponse getById(@PathVariable Integer id) {
        return mediator.send(new GetMemberStatusQuery(id));
    }

    @PutMapping("/{id}")
    public UpdatedMemberStatusResponse update(@PathVariable Integer id, @Valid @RequestBody UpdateMemberStatusCommand command) {
        command.setId(id);
        return mediator.send(command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        mediator.send(new DeleteMemberStatusCommand(id));
    }
}
