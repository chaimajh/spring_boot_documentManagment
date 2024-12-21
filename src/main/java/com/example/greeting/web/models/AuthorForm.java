package com.example.greeting.web.models;

public class AuthorForm {

    private String firstName;
    private String lastName;

    // Constructeur
    public AuthorForm(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Constructeur par défaut
    public AuthorForm() {
    }

    // Getters et Setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
