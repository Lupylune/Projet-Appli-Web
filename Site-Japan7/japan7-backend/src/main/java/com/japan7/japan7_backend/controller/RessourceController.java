package com.japan7.japan7_backend.controller;

import com.japan7.japan7_backend.model.Ressource;
import com.japan7.japan7_backend.repository.RessourceRepository;
import com.japan7.japan7_backend.repository.EvenementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ressources")
@CrossOrigin(origins = "*")
public class RessourceController {

    @Autowired
    private RessourceRepository ressourceRepository;

    @Autowired
    private EvenementRepository evenementRepository;

    @GetMapping
    public List<Ressource> getAll() {
        return ressourceRepository.findAll();
    }

    @GetMapping("/evenement/{evenementId}")
    public List<Ressource> getByEvenement(@PathVariable Long evenementId) {
        return ressourceRepository.findByEvenementId(evenementId);
    }

    @PostMapping
    public Ressource create(@RequestBody Ressource ressource) {
        if (ressource.getEvenement() == null || ressource.getEvenement().getId() == null) {
            throw new IllegalArgumentException("L'événement doit être spécifié avec un ID.");
        }

        var evenement = evenementRepository.findById(ressource.getEvenement().getId())
                .orElseThrow(() -> new IllegalArgumentException("Événement introuvable."));

        ressource.setEvenement(evenement);
        return ressourceRepository.save(ressource);
    }

    @PutMapping("/{id}")
    public Ressource update(@PathVariable Long id, @RequestBody Ressource updated) {
        Ressource r = ressourceRepository.findById(id).orElseThrow();
        r.setNom(updated.getNom());
        r.setType(updated.getType());
        r.setQuantite(updated.getQuantite());

        if (updated.getEvenement() != null && updated.getEvenement().getId() != null) {
            var evt = evenementRepository.findById(updated.getEvenement().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Événement introuvable"));
            r.setEvenement(evt);
        }

        return ressourceRepository.save(r);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        ressourceRepository.deleteById(id);
    }
}
