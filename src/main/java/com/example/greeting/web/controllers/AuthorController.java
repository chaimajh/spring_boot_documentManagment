package com.example.greeting.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.greeting.business.services.AuthorsService;
import com.example.greeting.dao.entities.Author;
import com.example.greeting.web.models.AuthorForm;

import jakarta.validation.Valid;
import java.util.List;

/*
 * CRUD operations Map
 * 
 * Create : /authors/create (Get: pour récupérer le formulaire d'ajout d'un nouvel auteur)
 *                         (Post: pour ajouter un auteur à la table authors)
 * Read : /authors (Get)
 *      
 * Update : /authors/{id}/edit (Get: pour récupérer le formulaire de modification d'un auteur)
 *                             (Post : pour modifier un auteur par son id)
 * Delete: /authors/{id}/delete (Post : permet de supprimer un auteur par son id)
 */
@Controller
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorsService authorService;

    @GetMapping("")
    public String listAuthors(Model model) {
        List<Author> authors = authorService.getAllAuthors();
        model.addAttribute("authors", authors);
        return "authors/authorList"; // Nom du fichier HTML/Thymeleaf pour afficher la liste des auteurs
    }

    @GetMapping("/create")
    public String showFormForAdd(Model model) {
        AuthorForm authorForm = new AuthorForm();
        model.addAttribute("authorForm", authorForm);
        return "authors/authorAddForm"; // Nom du fichier HTML/Thymeleaf pour le formulaire d'ajout d'auteur
    }

    @PostMapping("/create")
    public String saveAuthor(@Valid @ModelAttribute("authorForm") AuthorForm authorForm,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "authors/authorAddForm"; // Si des erreurs existent, renvoyer le formulaire
        }
        if (authorService.addAuthor(new Author(null, authorForm.getFirstName(), authorForm.getLastName())) == null) {
            model.addAttribute("duplicatedName", "Author name already exists.");
            return "authors/authorAddForm"; // Si le nom existe déjà, afficher un message d'erreur
        }
        return "redirect:/authors"; // Rediriger vers la liste des auteurs après l'ajout
    }

    @GetMapping("/{id}/edit")
    public String updateAuthor(@PathVariable Long id, Model model) {
        Author author = authorService.getAuthorById(id);
        model.addAttribute("authorForm", new AuthorForm(author.getFirstName(), author.getLastName()));
        model.addAttribute("id", author.getId());
        return "authors/authorEditForm"; // Nom du fichier HTML/Thymeleaf pour le formulaire de mise à jour d'auteur
    }

    @PostMapping("/{id}/edit")
    public String updateAuthor(@PathVariable Long id, @ModelAttribute("authorForm") AuthorForm authorForm) {
        authorService.updateAuthor(id, new Author(null, authorForm.getFirstName(), authorForm.getLastName()));
        return "redirect:/authors"; // Rediriger vers la liste des auteurs après la mise à jour
    }

    @PostMapping("/{id}/delete")
    public String deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return "redirect:/authors"; // Rediriger vers la liste des auteurs après la suppression
    }
}
