package com.turkcell.library.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;

import com.turkcell.library.service.MemberService;
import com.turkcell.library.dto.*;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService service;

    public MemberController(MemberService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedMemberResponse create(@RequestBody CreateMemberRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<ListMemberResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetMemberResponse getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public UpdatedMemberResponse update(@PathVariable Integer id, @RequestBody UpdateMemberRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
