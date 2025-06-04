package com.japan7.japan7_backend.controller;

import com.japan7.japan7_backend.dto.CommentaireDTO;
import com.japan7.japan7_backend.model.Commentaire;
import com.japan7.japan7_backend.model.Evenement;
import com.japan7.japan7_backend.model.Membre;
import com.japan7.japan7_backend.repository.CommentaireRepository;
import com.japan7.japan7_backend.repository.EvenementRepository;
import com.japan7.japan7_backend.repository.MembreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/commentaires")
@CrossOrigin(origins = "*")
public class CommentaireController {

    @Autowired
    private MembreRepository membreRepo;

    @Autowired
    private CommentaireRepository commentaireRepository;

    @Autowired
    private EvenementRepository evenementRepository;

    @GetMapping
    public List<CommentaireDTO> getAllCommentaires() {
        return commentaireRepository.findAll().stream()
                .map(CommentaireDTO::new)
                .collect(Collectors.toList());
    }


    @GetMapping("/evenement/{evenementId}")
    public List<Commentaire> getCommentairesParEvenement(@PathVariable Long evenementId) {
        return commentaireRepository.findByEvenementId(evenementId);
    }

    @PostMapping
    public ResponseEntity<?> ajouterCommentaire(@RequestBody Map<String, Object> payload) {
        Long membreId = Long.valueOf(String.valueOf(payload.get("auteur")));
        Membre membre = membreRepo.findById(membreId).get();
        String contenu = (String) payload.get("contenu");
        Long evenementId = Long.valueOf(payload.get("evenementId").toString());

        Evenement evenement = evenementRepository.findById(evenementId).orElse(null);
        if (evenement == null) {
            return ResponseEntity.badRequest().body("Événement introuvable.");
        }

        Commentaire commentaire = new Commentaire();
        commentaire.setAuteur(membre);
        commentaire.setContenu(contenu);
        commentaire.setDate(LocalDateTime.now());
        commentaire.setEvenement(evenement);

        commentaireRepository.save(commentaire);
        return ResponseEntity.ok("Commentaire ajouté.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> modifierCommentaire(@PathVariable Long id, @RequestBody Map<String, Object> payload) {
        Long membreId = Long.valueOf((String.valueOf(payload.get("auteur"))));
        Membre membre = membreRepo.findById(membreId).get();
        String contenu = (String) payload.get("contenu");
        Long evenementId = Long.valueOf((String) payload.get("evenementId"));
        return commentaireRepository.findById(id)
                .map(commentaire -> {
                    commentaire.setAuteur(membre);
                    commentaire.setContenu(contenu);
                    commentaire.setDate(LocalDateTime.now());

                    Evenement evenement = evenementRepository.findById(evenementId).orElse(null);
                    if (evenement == null) {
                        return ResponseEntity.badRequest().body("Événement introuvable.");
                    }
                    commentaire.setEvenement(evenement);

                    commentaireRepository.save(commentaire);
                    return ResponseEntity.ok("Commentaire modifié.");
                })
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> supprimerCommentaire(@PathVariable Long id) {
        if (!commentaireRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        commentaireRepository.deleteById(id);
        return ResponseEntity.ok("Commentaire supprimé.");
    }

}
