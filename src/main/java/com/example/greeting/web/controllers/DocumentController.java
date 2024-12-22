package com.example.greeting.web.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.greeting.business.services.DocumentService;
import com.example.greeting.dao.entities.Document;
import com.example.greeting.web.models.requests.DocumentForm;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/documents")
public class DocumentController {

    private final DocumentService documentService;

    @Value("${upload.directory}")
    private String uploadDirectory;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping()
    public String getAllDocuments(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "3") int pageSize,
                                  Model model) {
        Page<Document> documentPage = documentService.getAllDocumentsPagination(PageRequest.of(page, pageSize));
        model.addAttribute("documents", documentPage.getContent());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", documentPage.getTotalPages());
        return "document-list";
    }

    @GetMapping("/create")
    public String showAddDocumentForm(Model model) {
        model.addAttribute("documentForm", new DocumentForm());
        return "add-document";
    }

    @PostMapping("/create")
    public String addDocument(@Valid @ModelAttribute DocumentForm documentForm,
                              BindingResult bindingResult,
                              @RequestParam MultipartFile file,
                              Model model) {
        // Vérification des erreurs dans le formulaire
        if (bindingResult.hasErrors()) {
            return "add-document";
        }

        // Vérification que le fichier n'est pas vide
        if (file.isEmpty()) {
            model.addAttribute("error", "File is required");
            return "add-document";
        }

        try {
            // Créer le dossier si il n'existe pas
            Path uploadPath = Paths.get(uploadDirectory);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath); // Créer le dossier s'il n'existe pas
            }
          
            // Enregistrer le fichier dans le dossier spécifié
            String fileName = file.getOriginalFilename();
            Path newFilePath = uploadPath.resolve(fileName);
            Files.write(newFilePath, file.getBytes());
        model.addAttribute("documentForm",documentForm);
            // Créer un nouvel objet Document
            
            
             // Définir le type de fichier
                Document document = new Document(documentForm.getTitle(),
                documentForm.getDescription(),
                fileName);
            // Sauvegarder le document dans la base de données
            documentService.saveDocument(document);
            return "redirect:/documents"; // Rediriger vers la liste des documents
        } catch (IOException e) {
            model.addAttribute("error", "Failed to upload file.");
            return "add-document";
        }
    }

    @GetMapping("/{id}/edit")
    public String showUpdateDocumentForm(@PathVariable Long id, Model model) {
        Document document = documentService.getDocumentById(id);
        model.addAttribute("documentForm", new DocumentForm(document.getTitle(), document.getDescription()));
        model.addAttribute("id", id);
        return "update-document";
    }

    @PostMapping("/{id}/edit")
    public String updateDocument(@Valid @ModelAttribute DocumentForm documentForm,
                                 BindingResult bindingResult,
                                 @PathVariable Long id,
                                 @RequestParam MultipartFile file,
                                 Model model) {
        // Vérification des erreurs dans le formulaire
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Invalid input");
            return "update-document";
        }

        Document document = documentService.getDocumentById(id);
        document.setTitle(documentForm.getTitle());
        document.setDescription(documentForm.getDescription());

        // Si un fichier est sélectionné, mettre à jour le fichier
        if (!file.isEmpty()) {
            try {
                // Créer le dossier si il n'existe pas
                Path uploadPath = Paths.get(uploadDirectory);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                // Enregistrer le nouveau fichier
                String fileName = file.getOriginalFilename();
                Path newFilePath = uploadPath.resolve(fileName);
                Files.write(newFilePath, file.getBytes());
                document.setFileType(fileName); // Définir le type de fichier
            } catch (IOException e) {
                model.addAttribute("error", "Failed to upload file.");
                return "update-document";
            }
        }

        // Sauvegarder les modifications du document dans la base de données
        documentService.saveDocument(document);
        return "redirect:/documents"; // Rediriger vers la liste des documents
    }

    // @GetMapping("/{id}/delete")
    // public String deleteDocument(@PathVariable Long id) {
    //     documentService.deleteDocument(id);
    //     return "redirect:/documents"; // Rediriger vers la liste des documents après suppression
    // }
    @PostMapping("/{id}/delete")
    public String deleteDocument(@PathVariable Long id) {
        documentService.deleteDocument(id);
        return "redirect:/documents"; // Rediriger vers la liste des auteurs après la suppression
    }
}
