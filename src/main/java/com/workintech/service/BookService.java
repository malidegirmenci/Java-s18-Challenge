package com.workintech.service;

import com.workintech.dto.BookResponse;
import com.workintech.entity.Book;

import java.util.List;

public interface BookService{
    Book findById(Long id);
    BookResponse getById(Long id);
    BookResponse save(Book book, Long categoryId);
    BookResponse save(Book book, Long categoryId, Long authorId);
    List<BookResponse> findAll();
    BookResponse delete(Long id);
    BookResponse update(Long id,Book book);
}
