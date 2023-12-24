package com.workintech.controller;

import com.workintech.dto.BookResponse;
import com.workintech.entity.Book;
import com.workintech.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    @PostMapping("/{categoryId}")
    public BookResponse save(@RequestBody Book book, @PathVariable Long categoryId){
        return bookService.save(book,categoryId);
    }
    @PostMapping
    public BookResponse save(@RequestBody Book book, @RequestParam Long categoryId, @RequestParam Long authorId){
        return bookService.save(book,categoryId,authorId);
    }
    @GetMapping
    public List<BookResponse> findAll(){
        return bookService.findAll();
    }
    @GetMapping("/{id}")
    public BookResponse getById(@PathVariable Long id){
        return bookService.getById(id);
    }

    @DeleteMapping("/{id}")
    public BookResponse delete(@PathVariable Long id){
        return bookService.delete(id);
    }

    @PutMapping("/{id}")
    public BookResponse update(@PathVariable Long id, @RequestBody Book book){
        return bookService.update(id,book);
    }
}
