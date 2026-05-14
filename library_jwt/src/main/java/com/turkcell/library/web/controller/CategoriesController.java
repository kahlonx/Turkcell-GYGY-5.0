package com.turkcell.library.web.controller;

import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import java.util.List;

import com.turkcell.library.core.mediator.Mediator;
import com.turkcell.library.application.features.category.command.create.CreateCategoryCommand;
import com.turkcell.library.application.features.category.command.create.CreatedCategoryResponse;
import com.turkcell.library.application.features.category.command.update.UpdateCategoryCommand;
import com.turkcell.library.application.features.category.command.update.UpdatedCategoryResponse;
import com.turkcell.library.application.features.category.command.delete.DeleteCategoryCommand;
import com.turkcell.library.application.features.category.query.get.GetCategoryQuery;
import com.turkcell.library.application.features.category.query.get.GetCategoryResponse;
import com.turkcell.library.application.features.category.query.list.ListCategoryQuery;
import com.turkcell.library.application.features.category.query.list.ListCategoryResponse;

@RestController
@RequestMapping("/api/categories")
public class CategoriesController {

    private final Mediator mediator;

    public CategoriesController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedCategoryResponse create(@Valid @RequestBody CreateCategoryCommand command) {
        return mediator.send(command);
    }

    @GetMapping
    public List<ListCategoryResponse> getAll() {
        return mediator.send(new ListCategoryQuery());
    }

    @GetMapping("/{id}")
    public GetCategoryResponse getById(@PathVariable Integer id) {
        return mediator.send(new GetCategoryQuery(id));
    }

    @PutMapping("/{id}")
    public UpdatedCategoryResponse update(@PathVariable Integer id, @Valid @RequestBody UpdateCategoryCommand command) {
        command.setId(id);
        return mediator.send(command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        mediator.send(new DeleteCategoryCommand(id));
    }
}
