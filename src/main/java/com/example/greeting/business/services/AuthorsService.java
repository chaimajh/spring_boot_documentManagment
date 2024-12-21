package com.example.greeting.business.services;

import java.util.List;

import com.example.greeting.dao.entities.Author;

public interface AuthorsService {
    Author addAuthor(Author author);

    Author getAuthorById(Long id);

    List<Author> getAllAuthors();

    void deleteAuthor(Long id);

    Author updateAuthor(Long id, Author author);
}
