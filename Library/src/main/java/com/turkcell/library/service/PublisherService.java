package com.turkcell.library.service;

import com.turkcell.library.dto.*;
import java.util.List;

public interface PublisherService {
    CreatedPublisherResponse create(CreatePublisherRequest request);
    List<ListPublisherResponse> getAll();
    GetPublisherResponse getById(Integer id);
    UpdatedPublisherResponse update(Integer id, UpdatePublisherRequest request);
    void delete(Integer id);
}
