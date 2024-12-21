package com.example.greeting.dao.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "documents")
public class Document {

// Constructor with only title, fileType, and description
public Document(String title, String fileType, String description) {
    this.title = title;
    this.fileType = fileType;
    this.description = description;
}

// Default Constructor
public Document() {
}


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title cannot be blank")
    @Size(max = 100, message = "Title must not exceed 100 characters")
    private String title;


    @Size(max = 50, message = "Author name must not exceed 50 characters")
    @Column( length = 50)
    private String author;

    @Size(max = 50, message = "Theme must not exceed 50 characters")
    private String theme;

    @Size(max = 500, message = "Summary must not exceed 500 characters")
    private String summary;

    @Size(max = 200, message = "Keywords must not exceed 200 characters")
    private String keywords;
    private String description;

    @Column(name = "publication_date")
    private LocalDate publicationDate;

    private String fileType;
    private String content;
     private String name;


    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getFileType() {
        return fileType;
    }
    public String getContent() {
        return content;
    }
    

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        description = description;
    }
}
