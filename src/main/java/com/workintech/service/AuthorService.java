package com.workintech.service;

import com.workintech.dto.AuthorResponse;
import com.workintech.dto.BookResponse;
import com.workintech.entity.Author;

import java.util.List;

public interface AuthorService {
    Author findById(Long id);
    AuthorResponse getById(Long id);
    AuthorResponse save(Author author);
    AuthorResponse saveWithBook(Author author,Long bookId);
    List<AuthorResponse> findAll();
    AuthorResponse update(Author author, Long id);
    AuthorResponse delete(Long id);
    List<BookResponse> getAllBooksOfAuthor(Long id);
}
