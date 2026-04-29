package com.turkcell.library.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import com.turkcell.library.dto.*;
import com.turkcell.library.entity.Author;
import com.turkcell.library.repository.AuthorRepository;
import com.turkcell.library.exception.type.NotFoundException;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository repository;

    public AuthorServiceImpl(AuthorRepository repository) {
        this.repository = repository;
    }

    @Override
    public CreatedAuthorResponse create(CreateAuthorRequest request) {
        Author entity = new Author();
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity = repository.save(entity);
        CreatedAuthorResponse response = new CreatedAuthorResponse();
        response.setId(entity.getId());
        return response;
    }

    @Override
    public List<ListAuthorResponse> getAll() {
        List<Author> entities = repository.findAll();
        List<ListAuthorResponse> responses = new ArrayList<>();
        for (Author entity : entities) {
            ListAuthorResponse response = new ListAuthorResponse();
            response.setId(entity.getId());
            responses.add(response);
        }
        return responses;
    }

    @Override
    public GetAuthorResponse getById(Integer id) {
        Author entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Author not found"));
        GetAuthorResponse response = new GetAuthorResponse();
        response.setId(entity.getId());
        return response;
    }

    @Override
    public UpdatedAuthorResponse update(Integer id, UpdateAuthorRequest request) {
        Author entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Author not found"));
        if (request.getFirstName() != null) {
            entity.setFirstName(request.getFirstName());
        }
        if (request.getLastName() != null) {
            entity.setLastName(request.getLastName());
        }
        entity = repository.save(entity);
        UpdatedAuthorResponse response = new UpdatedAuthorResponse();
        response.setId(entity.getId());
        return response;
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}