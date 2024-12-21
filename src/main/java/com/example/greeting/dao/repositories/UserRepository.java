package com.example.greeting.dao.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.greeting.dao.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByEmail(String eamil);
}