package com.turkcell.library.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import com.turkcell.library.dto.*;
import com.turkcell.library.entity.Librarian;
import com.turkcell.library.repository.LibrarianRepository;

@Service
public class LibrarianServiceImpl implements LibrarianService {
    private final LibrarianRepository repository;

    public LibrarianServiceImpl(LibrarianRepository repository) {
        this.repository = repository;
    }

    @Override
    public CreatedLibrarianResponse create(CreateLibrarianRequest request) {
        Librarian entity = new Librarian();
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity = repository.save(entity);
        CreatedLibrarianResponse response = new CreatedLibrarianResponse();
        response.setId(entity.getId());
        return response;
    }

    @Override
    public List<ListLibrarianResponse> getAll() {
        List<Librarian> entities = repository.findAll();
        List<ListLibrarianResponse> responses = new ArrayList<>();
        for (Librarian entity : entities) {
            ListLibrarianResponse response = new ListLibrarianResponse();
            response.setId(entity.getId());
            responses.add(response);
        }
        return responses;
    }

    @Override
    public GetLibrarianResponse getById(Integer id) {
        Librarian entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Entity not found"));
        GetLibrarianResponse response = new GetLibrarianResponse();
        response.setId(entity.getId());
        return response;
    }

    @Override
    public UpdatedLibrarianResponse update(Integer id, UpdateLibrarianRequest request) {
        Librarian entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Entity not found"));
        if (request.getFirstName() != null) {
            entity.setFirstName(request.getFirstName());
        }
        if (request.getLastName() != null) {
            entity.setLastName(request.getLastName());
        }
        entity = repository.save(entity);
        UpdatedLibrarianResponse response = new UpdatedLibrarianResponse();
        response.setId(entity.getId());
        return response;
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
