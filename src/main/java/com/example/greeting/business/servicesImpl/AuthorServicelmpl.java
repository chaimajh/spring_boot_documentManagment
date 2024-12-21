package com.example.greeting.business.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.greeting.business.services.AuthorsService;
import com.example.greeting.dao.entities.Author;
import com.example.greeting.dao.repositories.AuthorRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthorServicelmpl implements AuthorsService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Author addAuthor(Author author) {
        if (author == null) {
            return null;
        }
        try {
            return authorRepository.save(author);
        } catch (DataIntegrityViolationException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public Author getAuthorById(Long id) {
        if (id == null) {
            return null;
        }
        return authorRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Author with id: " + id + " not found"));
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public void deleteAuthor(Long id) {
        if (id == null) {
            return;
        }
        if (authorRepository.existsById(id)) {
            authorRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Author with id: " + id + " not found");
        }
    }

    @Override
    public Author updateAuthor(Long id, Author author) {
        if (author == null) {
            return null;
        }
        Author existingAuthor = getAuthorById(id);
        existingAuthor.setFirstName(author.getFirstName());
        existingAuthor.setLastName(author.getLastName());
        return authorRepository.save(existingAuthor);
    }
}
