package com.turkcell.library.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import com.turkcell.library.dto.*;
import com.turkcell.library.entity.Book;
import com.turkcell.library.repository.BookRepository;
import com.turkcell.library.entity.Category;
import com.turkcell.library.repository.CategoryRepository;
import com.turkcell.library.exception.type.NotFoundException;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository repository;
    private final CategoryRepository categoryRepository;

    public BookServiceImpl(BookRepository repository, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CreatedBookResponse create(CreateBookRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Category not found"));
        Book entity = new Book();
        entity.setTitle(request.getTitle());
        entity.setPublicationDate(request.getPublicationDate());
        entity.setCopiesOwned(request.getCopiesOwned());
        entity.setBarcodeNo(request.getBarcodeNo());
        entity.setCategory(category);
        entity = repository.save(entity);
        CreatedBookResponse response = new CreatedBookResponse();
        response.setId(entity.getId());
        return response;
    }

    @Override
    public List<ListBookResponse> getAll() {
        List<Book> entities = repository.findAll();
        List<ListBookResponse> responses = new ArrayList<>();
        for (Book entity : entities) {
            ListBookResponse response = new ListBookResponse();
            response.setId(entity.getId());
            responses.add(response);
        }
        return responses;
    }

    @Override
    public GetBookResponse getById(Integer id) {
        Book entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Book not found"));
        GetBookResponse response = new GetBookResponse();
        response.setId(entity.getId());
        return response;
    }

    @Override
    public UpdatedBookResponse update(Integer id, UpdateBookRequest request) {
        Book entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Book not found"));
        if (request.getCategoryId() != null) {
            Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new NotFoundException("Category not found"));
            entity.setCategory(category);
        }
        if (request.getTitle() != null) {
            entity.setTitle(request.getTitle());
        }
        if (request.getPublicationDate() != null) {
            entity.setPublicationDate(request.getPublicationDate());
        }
        if (request.getCopiesOwned() != null) {
            entity.setCopiesOwned(request.getCopiesOwned());
        }
        if (request.getBarcodeNo() != null) {
            entity.setBarcodeNo(request.getBarcodeNo());
        }
        entity = repository.save(entity);
        UpdatedBookResponse response = new UpdatedBookResponse();
        response.setId(entity.getId());
        return response;
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}