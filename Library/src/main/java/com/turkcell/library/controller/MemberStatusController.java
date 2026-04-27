package com.turkcell.library.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;

import com.turkcell.library.service.MemberStatusService;
import com.turkcell.library.dto.*;

@RestController
@RequestMapping("/api/member-statuses")
public class MemberStatusController {

    private final MemberStatusService service;

    public MemberStatusController(MemberStatusService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedMemberStatusResponse create(@RequestBody CreateMemberStatusRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<ListMemberStatusResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetMemberStatusResponse getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public UpdatedMemberStatusResponse update(@PathVariable Integer id, @RequestBody UpdateMemberStatusRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
