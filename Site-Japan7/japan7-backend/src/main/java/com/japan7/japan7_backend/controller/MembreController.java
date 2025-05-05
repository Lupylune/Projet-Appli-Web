package com.japan7.japan7_backend.controller;

import com.japan7.japan7_backend.model.Membre;
import com.japan7.japan7_backend.repository.MembreRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }

    @PutMapping("/{id}")
    public Membre update(@PathVariable Long id, @RequestBody Membre membre) {
        return repo.findById(id).map(m -> {
            m.setNom(membre.getNom());
            m.setPrenom(membre.getPrenom());
            m.setEmail(membre.getEmail());
            return repo.save(m);
        }).orElseThrow(() -> new RuntimeException("Membre non trouvé"));
    }
}
