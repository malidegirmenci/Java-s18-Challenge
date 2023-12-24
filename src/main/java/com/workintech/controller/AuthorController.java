package com.workintech.controller;

import com.workintech.dto.AuthorResponse;
import com.workintech.dto.BookResponse;
import com.workintech.entity.Author;
import com.workintech.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/author")
public class AuthorController {
    private final AuthorService authorService;

    @PostMapping
    public AuthorResponse save(@RequestBody Author author) {
        return authorService.save(author);
    }

    @PostMapping("/{bookId}")
    public AuthorResponse save(@RequestBody Author author, @PathVariable Long bookId) {
        return authorService.saveWithBook(author, bookId);
    }

    @PutMapping("/{id}")
    public AuthorResponse update(@RequestBody Author author, @PathVariable Long id) {
        return authorService.update(author, id);
    }

    @GetMapping
    public List<AuthorResponse> getAll() {
        return authorService.findAll();
    }

    @GetMapping("/{id}")
    public AuthorResponse getById(@PathVariable Long id) {
        return authorService.getById(id);
    }

    @GetMapping("/{id}/books")
    public List<BookResponse> getAllBooksOfAuthor(@PathVariable Long id) {
        return authorService.getAllBooksOfAuthor(id);
    }

    @DeleteMapping("/{id}")
    public AuthorResponse delete(@PathVariable Long id) {
        return authorService.delete(id);
    }

}
