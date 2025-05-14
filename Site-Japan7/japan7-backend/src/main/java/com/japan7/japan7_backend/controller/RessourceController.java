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
        return ressourceRepository.save(ressource);
    }

    @PutMapping("/{id}")
    public Ressource update(@PathVariable Long id, @RequestBody Ressource updated) {
        Ressource r = ressourceRepository.findById(id).orElseThrow();
        r.setNom(updated.getNom());
        r.setType(updated.getType());
        r.setQuantite(updated.getQuantite());
        r.setEvenement(updated.getEvenement());
        return ressourceRepository.save(r);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        ressourceRepository.deleteById(id);
    }
}
