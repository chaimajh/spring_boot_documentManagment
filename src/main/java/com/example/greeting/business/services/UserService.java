package com.example.greeting.business.services;

import com.example.greeting.dao.entities.User;

// Définition d'une interface pour les opérations relatives aux utilisateurs
public interface UserService {
    
    // Méthode pour sauvegarder un utilisateur
    User saveUser(User user);
}
