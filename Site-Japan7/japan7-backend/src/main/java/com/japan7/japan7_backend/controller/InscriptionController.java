package com.japan7.japan7_backend.controller;

import com.japan7.japan7_backend.dto.InscriptionDTO;
import com.japan7.japan7_backend.model.Evenement;
import com.japan7.japan7_backend.model.Inscription;
import com.japan7.japan7_backend.model.Membre;
import com.japan7.japan7_backend.repository.EvenementRepository;
import com.japan7.japan7_backend.repository.InscriptionRepository;
import com.japan7.japan7_backend.repository.MembreRepository;

import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/check")
    public boolean isInscrit(@RequestParam Long membreId, @RequestParam Long evenementId) {
        return repo.existsByMembreIdAndEvenementId(membreId, evenementId);
    }

    @PostMapping
    public ResponseEntity<?> inscrireMembre(@RequestBody InscriptionDTO dto) {
        Optional<Membre> membreOpt = membreRepo.findByEmail(dto.getEmail());
        if (membreOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Membre inexistant.");
        }
        Membre membre = membreOpt.get();

        Evenement evenement = evenementRepo.findById(dto.getEvenementId()).orElse(null);
        if (evenement == null) {
            return ResponseEntity.badRequest().body("Événement introuvable.");
        }

        boolean dejaInscrit = repo.findAll().stream().anyMatch(i ->
                i.getMembre().getId().equals(membre.getId()) &&
                        i.getEvenement().getId().equals(evenement.getId()));

        if (dejaInscrit) {
            return ResponseEntity.badRequest().body("Vous êtes déjà inscrit à cet événement.");
        }

        Inscription inscription = new Inscription(membre, evenement);
        repo.save(inscription);

        return ResponseEntity.ok("Inscription enregistrée.");
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }

    @DeleteMapping
    @Transactional
    public void deleteInscription(
            @RequestParam Long membreId,
            @RequestParam Long evenementId) {
        System.out.println("DeleteInscription");
        repo.deleteByMembreIdAndEvenementId(membreId, evenementId);
    }

    @GetMapping("/evenement/{evenementId}")
    public List<Inscription> getInscriptionsParEvenement(@PathVariable Long evenementId) {
        return repo.findByEvenementId(evenementId);
    }

}
