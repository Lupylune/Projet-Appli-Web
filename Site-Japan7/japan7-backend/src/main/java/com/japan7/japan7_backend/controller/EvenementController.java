package com.japan7.japan7_backend.controller;

import com.japan7.japan7_backend.model.Evenement;
import com.japan7.japan7_backend.repository.EvenementRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/evenements")
@CrossOrigin(origins = "*") // Autorise les appels depuis le frontend local
public class EvenementController {

    private final EvenementRepository repo;

    public EvenementController(EvenementRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Evenement> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Evenement> getById(@PathVariable Long id) {
        return repo.findById(id);
    }

    @PostMapping
    public Evenement create(@RequestBody Evenement evt) {
        return repo.save(evt);
    }

    @PutMapping("/{id}")
    public Evenement update(@PathVariable Long id, @RequestBody Evenement evt) {
        return repo.findById(id).map(e -> {
            e.setTitre(evt.getTitre());
            e.setDescription(evt.getDescription());
            e.setDate(evt.getDate());
            e.setTypeActivite(evt.getTypeActivite());
            return repo.save(e);
        }).orElseThrow(() -> new RuntimeException("Événement non trouvé"));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
