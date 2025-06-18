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
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());
        return appUserRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        appUserRepository.deleteById(id);
    }

    public AppUser login(String email, String password) {
        Optional<AppUser> optionalUser = appUserRepository.findByEmail(email);

        if (optionalUser.isEmpty()) {
            System.out.println("❌ Login failed: user not found for email " + email);
            return null;
        }

        AppUser user = optionalUser.get();

        if (user.getPassword() == null || !user.getPassword().equals(password)) {
            System.out.println("❌ Login failed: invalid password for email " + email);
            return null;
        }

        System.out.println("✅ Login successful for email: " + email);
        return user;
    }
}
