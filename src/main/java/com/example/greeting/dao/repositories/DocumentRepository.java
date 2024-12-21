package com.example.greeting.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.greeting.dao.entities.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    // JpaRepository provides the findAll(Pageable pageable) method
}
