package com.turkcell.library.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import com.turkcell.library.dto.*;
import com.turkcell.library.entity.Publisher;
import com.turkcell.library.repository.PublisherRepository;

@Service
public class PublisherServiceImpl implements PublisherService {
    private final PublisherRepository repository;

    public PublisherServiceImpl(PublisherRepository repository) {
        this.repository = repository;
    }

    @Override
    public CreatedPublisherResponse create(CreatePublisherRequest request) {
        Publisher entity = new Publisher();
        entity.setPublisherName(request.getPublisherName());
        entity = repository.save(entity);
        CreatedPublisherResponse response = new CreatedPublisherResponse();
        response.setId(entity.getId());
        return response;
    }

    @Override
    public List<ListPublisherResponse> getAll() {
        List<Publisher> entities = repository.findAll();
        List<ListPublisherResponse> responses = new ArrayList<>();
        for (Publisher entity : entities) {
            ListPublisherResponse response = new ListPublisherResponse();
            response.setId(entity.getId());
            responses.add(response);
        }
        return responses;
    }

    @Override
    public GetPublisherResponse getById(Integer id) {
        Publisher entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Entity not found"));
        GetPublisherResponse response = new GetPublisherResponse();
        response.setId(entity.getId());
        return response;
    }

    @Override
    public UpdatedPublisherResponse update(Integer id, UpdatePublisherRequest request) {
        Publisher entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Entity not found"));
        if (request.getPublisherName() != null) {
            entity.setPublisherName(request.getPublisherName());
        }
        entity = repository.save(entity);
        UpdatedPublisherResponse response = new UpdatedPublisherResponse();
        response.setId(entity.getId());
        return response;
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
