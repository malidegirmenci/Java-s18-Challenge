package com.workintech.dto;

public record BookResponse(Long id, String name, CategoryResponse categoryResponse, AuthorResponse authorResponse ) {
}
