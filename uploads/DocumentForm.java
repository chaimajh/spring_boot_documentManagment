package com.example.greeting.web.models;

import jakarta.validation.constraints.NotEmpty;

public class DocumentForm {
    @NotEmpty(message = "Title is required")
    private String title;

    @NotEmpty(message = "Content is required")
    private String content;

    // Constructeurs
    public DocumentForm() {}

    public DocumentForm(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // Getters et setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
