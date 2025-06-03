package com.japan7.japan7_backend.controller;

import com.japan7.japan7_backend.model.Membre;
import com.japan7.japan7_backend.repository.MembreRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/membres")
@CrossOrigin(origins = "*")
public class MembreController {

    private final MembreRepository repo;

    public MembreController(MembreRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Membre> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public Membre create(@RequestBody Membre membre) {

        return repo.save(membre);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Membre membre) {
        // Check if the email is already used
        if (repo.findByEmail(membre.getEmail()).isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Un compte avec cet email existe déjà.");
        }

        // If email is free, create the account
        Membre saved = repo.save(membre);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Membre membre) {
        // Check if the email already exists and is linked to another member
        Optional<Membre> existingByEmail = repo.findByEmail(membre.getEmail());
        if (existingByEmail.isPresent() && !existingByEmail.get().getId().equals(id)) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Un compte avec cet email existe déjà.");
        }

        // Update the member if they exist
        Optional<Membre> om = repo.findById(id);
        if (om.isPresent()) {
            Membre m = om.get();
            m.setNom(membre.getNom());
            m.setPrenom(membre.getPrenom());
            m.setEmail(membre.getEmail());
            if (membre.getPassword() != null && !membre.getPassword().isEmpty()) {
                m.setPassword(membre.getPassword());
            }
            return ResponseEntity.ok(repo.save(m));
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Membre non trouvé.");
        }
    }
}
