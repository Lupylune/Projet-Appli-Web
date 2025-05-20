package com.japan7.japan7_backend.controller;

import com.japan7.japan7_backend.model.Inscription;
import com.japan7.japan7_backend.model.Membre;
import com.japan7.japan7_backend.model.Evenement;
import com.japan7.japan7_backend.repository.InscriptionRepository;
import com.japan7.japan7_backend.repository.MembreRepository;
import com.japan7.japan7_backend.repository.EvenementRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inscriptions")
@CrossOrigin(origins = "*")
public class InscriptionController {

    private final InscriptionRepository repo;
    private final MembreRepository membreRepo;
    private final EvenementRepository evenementRepo;

    public InscriptionController(InscriptionRepository repo, MembreRepository membreRepo, EvenementRepository evenementRepo) {
        this.repo = repo;
        this.membreRepo = membreRepo;
        this.evenementRepo = evenementRepo;
    }

    @GetMapping
    public List<Inscription> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public Inscription inscrire(@RequestParam Long membreId, @RequestParam Long evenementId) {
        Membre membre = membreRepo.findById(membreId)
                .orElseThrow(() -> new RuntimeException("Membre introuvable"));
        Evenement evenement = evenementRepo.findById(evenementId)
                .orElseThrow(() -> new RuntimeException("Événement introuvable"));

        // Vérifie si l'inscription existe déjà
        List<Inscription> existantes = repo.findAll();
        boolean dejaInscrit = existantes.stream().anyMatch(i ->
                i.getMembre().getId().equals(membreId) && i.getEvenement().getId().equals(evenementId));

        if (dejaInscrit) {
            throw new RuntimeException("Ce membre est déjà inscrit à cet événement.");
        }

        return repo.save(new Inscription(membre, evenement));
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
