package com.workintech.converter;

import com.workintech.dto.AuthorResponse;
import com.workintech.dto.BookResponse;
import com.workintech.dto.CategoryResponse;
import com.workintech.entity.Author;
import com.workintech.entity.Book;
import com.workintech.entity.Category;

import java.util.ArrayList;
import java.util.List;

public class DtoConverter {
    public static BookResponse convertToBookResponse(Book book){
        return new BookResponse(
                book.getId(), book.getName(),
                new CategoryResponse(book.getCategory().getId(),book.getCategory().getName()),
                new AuthorResponse(book.getAuthor().getId(),book.getAuthor().getFullName()));
    }

    public static List<BookResponse> convertToBookResponseList(List<Book> books){
        List<BookResponse> responses = new ArrayList<>();
        books.forEach(book -> responses.add(new BookResponse(
                book.getId(), book.getName(),
                new CategoryResponse(book.getCategory().getId(),book.getCategory().getName()),
                new AuthorResponse(book.getAuthor().getId(),book.getAuthor().getFullName()))
        ));
        return responses;
    }

    public static AuthorResponse convertToAuthorResponse(Author author){
        return new AuthorResponse(
                author.getId(), author.getFullName()
        );
    }
    public static List<AuthorResponse> convertToAuthorResponseList(List<Author> authors){
        List<AuthorResponse> responses = new ArrayList<>();
        authors.forEach(category -> responses.add(new AuthorResponse(
                category.getId(),
                category.getFullName()
        )));
        return responses;
    }
    public static CategoryResponse convertToCategoryResponse(Category category){
        return new CategoryResponse(
                category.getId(), category.getName()
        );
    }

    public static List<CategoryResponse> convertToCategoryResponseList(List<Category> categories){
        List<CategoryResponse> responses = new ArrayList<>();
        categories.forEach(category -> responses.add(new CategoryResponse(
                category.getId(),
                category.getName()
        )));
        return responses;
    }
}
