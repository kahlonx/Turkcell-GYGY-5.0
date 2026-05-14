package com.turkcell.library.web.controller;

import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import java.util.List;

import com.turkcell.library.core.mediator.Mediator;
import com.turkcell.library.application.features.member.command.create.CreateMemberCommand;
import com.turkcell.library.application.features.member.command.create.CreatedMemberResponse;
import com.turkcell.library.application.features.member.command.update.UpdateMemberCommand;
import com.turkcell.library.application.features.member.command.update.UpdatedMemberResponse;
import com.turkcell.library.application.features.member.command.delete.DeleteMemberCommand;
import com.turkcell.library.application.features.member.query.get.GetMemberQuery;
import com.turkcell.library.application.features.member.query.get.GetMemberResponse;
import com.turkcell.library.application.features.member.query.list.ListMemberQuery;
import com.turkcell.library.application.features.member.query.list.ListMemberResponse;

@RestController
@RequestMapping("/api/members")
public class MembersController {

    private final Mediator mediator;

    public MembersController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedMemberResponse create(@Valid @RequestBody CreateMemberCommand command) {
        return mediator.send(command);
    }

    @GetMapping
    public List<ListMemberResponse> getAll() {
        return mediator.send(new ListMemberQuery());
    }

    @GetMapping("/{id}")
    public GetMemberResponse getById(@PathVariable Integer id) {
        return mediator.send(new GetMemberQuery(id));
    }

    @PutMapping("/{id}")
    public UpdatedMemberResponse update(@PathVariable Integer id, @Valid @RequestBody UpdateMemberCommand command) {
        command.setId(id);
        return mediator.send(command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        mediator.send(new DeleteMemberCommand(id));
    }
}
