package com.example.greeting.business.services;

import java.util.List;

import org.springframework.data.domain.Page; // Import the Page class
import org.springframework.data.domain.PageRequest; // Import the PageRequest class
import org.springframework.stereotype.Service;

import com.example.greeting.dao.entities.Document;

@Service
public interface DocumentService {
    Document saveDocument(Document document);
    List<Document> getAllDocuments();
    Document getDocumentById(Long id);
    void deleteDocument(Long id);
    
    // New method for pagination
    Page<Document> getAllDocumentsPagination(PageRequest pageRequest); 
}
