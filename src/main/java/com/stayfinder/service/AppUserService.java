package com.stayfinder.service;

import com.stayfinder.entity.AppUser;
import com.stayfinder.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    public AppUser saveUser(AppUser user) {
        // Basic null or empty checks
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }

        // Check if email already exists
        Optional<AppUser> existingUser = appUserRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }

        // Save user as-is (no password hashing for now)
        return appUserRepository.save(user);
    }

    public List<AppUser> getAllUsers() {
        return appUserRepository.findAll();
    }

    public AppUser getUserById(Long id) {
        return appUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public AppUser updateUser(Long id, AppUser updatedUser) {
        AppUser existingUser = getUserById(id);

        if (updatedUser.getUsername() != null) existingUser.setUsername(updatedUser.getUsername());
        if (updatedUser.getEmail() != null) existingUser.setEmail(updatedUser.getEmail());
        if (updatedUser.getPassword() != null) existingUser.setPassword(updatedUser.getPassword());

        return appUserRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        appUserRepository.deleteById(id);
    }

    public Optional<AppUser> findByEmail(String email) {
        return appUserRepository.findByEmail(email);
    }

    public AppUser login(String email, String password) {
        Optional<AppUser> optionalUser = appUserRepository.findByEmail(email);

        if (optionalUser.isEmpty()) {
            System.out.println("❌ Login failed: user not found for email " + email);
            return null;
        }

        AppUser user = optionalUser.get();

        if (user.getPassword() == null || !user.getPassword().equals(password)) {
            System.out.println("❌ Login failed: incorrect password for " + email);
            return null;
        }

        System.out.println("✅ Login successful for email: " + email);
        return user;
    }
}
