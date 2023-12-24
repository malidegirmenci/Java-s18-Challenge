package com.workintech.service;

import com.workintech.converter.DtoConverter;
import com.workintech.dto.BookResponse;
import com.workintech.entity.Author;
import com.workintech.entity.Book;
import com.workintech.entity.Category;
import com.workintech.exceptions.LibraryException;
import com.workintech.repository.AuthorRepository;
import com.workintech.repository.BookRepository;
import com.workintech.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BookServiceImpl implements BookService{
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final AuthorRepository authorRepository;
    @Override
    public Book findById(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isPresent()){
            return optionalBook.get();
        }
        throw new LibraryException("The book with given id could not find", HttpStatus.BAD_REQUEST);
    }
    public BookResponse getById(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isPresent()){
            return DtoConverter.convertToBookResponse(optionalBook.get());
        }
        throw new LibraryException("The book with given id could not find", HttpStatus.BAD_REQUEST);
    }
    private BookResponse save(Book book){
        return DtoConverter.convertToBookResponse(bookRepository.save(book));
    }
    @Override
    public BookResponse save(Book book, Long categoryId) {
        if(categoryRepository.findById(categoryId).isPresent()){
            Category category = categoryRepository.findById(categoryId).get();
            book.setCategory(category);
            category.addBook(book);
        }else{
            throw new LibraryException("Category with given id could not find",HttpStatus.BAD_REQUEST);
        }
        return DtoConverter.convertToBookResponse(bookRepository.save(book));
    }

    @Override
    public BookResponse save(Book book, Long categoryId, Long authorId) {
        if(categoryRepository.findById(categoryId).isPresent()){
            Category category = categoryRepository.findById(categoryId).get();
            book.setCategory(category);
            category.addBook(book);
        }else{
            throw new LibraryException("Category with given id could not find",HttpStatus.BAD_REQUEST);
        }
        if(authorRepository.findById(authorId).isPresent()){
            Author author = authorRepository.findById(authorId).get();
            book.setAuthor(author);
            author.addBook(book);
        }else{
            throw new LibraryException("Author with given id could not find",HttpStatus.BAD_REQUEST);
        }
        return DtoConverter.convertToBookResponse(bookRepository.save(book));
    }
    @Override
    public List<BookResponse> findAll(){
        return DtoConverter.convertToBookResponseList(bookRepository.findAll());
    }
    @Override
    public BookResponse delete(Long id){
        Book willDeleteBook = findById(id);
        bookRepository.delete(willDeleteBook);
        return DtoConverter.convertToBookResponse(willDeleteBook);
    }
    @Override
    public BookResponse update(Long id, Book book) {
        Book willUpdateBook = findById(id);
        willUpdateBook.setName(book.getName());
        willUpdateBook.setCategory(book.getCategory());
        willUpdateBook.setAuthor(book.getAuthor());
        return save(willUpdateBook);
    }
}
