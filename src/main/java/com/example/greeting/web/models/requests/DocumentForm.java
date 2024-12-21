package com.example.greeting.web.models.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
public class DocumentForm {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "File is required")
    private MultipartFile file;  // Champ pour le fichier

    // Constructeur par défaut
    public DocumentForm() {
    }

    // Constructeur
    public DocumentForm(String title, String description) {
        this.title = title;
        this.description = description;
    }

    // Getters et Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
