package com.workintech.service;

import com.workintech.converter.DtoConverter;
import com.workintech.dto.AuthorResponse;
import com.workintech.dto.BookResponse;
import com.workintech.entity.Author;
import com.workintech.entity.Book;
import com.workintech.exceptions.LibraryException;
import com.workintech.repository.AuthorRepository;
import com.workintech.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService{
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    @Override
    public List<AuthorResponse> findAll() {
        return DtoConverter.convertToAuthorResponseList(authorRepository.findAll());
    }
    @Override
    public Author findById(Long id) {
        Optional<Author> authorOptional = authorRepository.findById(id);
        if(authorOptional.isPresent()){
            return authorOptional.get();
        }
        throw new LibraryException("Author with given id could not find: "+id, HttpStatus.BAD_REQUEST);
    }
    @Override
    public AuthorResponse getById(Long id) {
        Optional<Author> authorOptional = authorRepository.findById(id);
        if(authorOptional.isPresent()){
            return DtoConverter.convertToAuthorResponse(authorOptional.get());
        }
        throw new LibraryException("Author with given id could not find: "+id, HttpStatus.BAD_REQUEST);
    }
    @Override
    public AuthorResponse save(Author author) {
        return DtoConverter.convertToAuthorResponse(authorRepository.save(author));
    }

    @Override
    public AuthorResponse saveWithBook(Author author,Long bookId) {
        if(bookRepository.findById(bookId).isPresent()){
            Book foundBook = bookRepository.findById(bookId).get();
            author.addBook(foundBook);
        }else{
            throw new LibraryException("Book with given id could not find",HttpStatus.BAD_REQUEST);
        }
        authorRepository.save(author);
        return DtoConverter.convertToAuthorResponse(author);
    }

    @Override
    public AuthorResponse update(Author author, Long id) {
        Author willUpdateAuthor = findById(id);
        willUpdateAuthor.setId(id);
        willUpdateAuthor.setFirstName(author.getFirstName());
        willUpdateAuthor.setLastName(author.getLastName());
        authorRepository.save(willUpdateAuthor);
        return DtoConverter.convertToAuthorResponse(willUpdateAuthor);
    }

    @Override
    public AuthorResponse delete(Long id) {
        Author willDeleteAuthor = findById(id);
        authorRepository.delete(willDeleteAuthor);
        return DtoConverter.convertToAuthorResponse(willDeleteAuthor);
    }

    @Override
    public List<BookResponse> getAllBooksOfAuthor(Long id) {
        Author author = findById(id);
        return DtoConverter.convertToBookResponseList(author.getBooks());
    }
}
