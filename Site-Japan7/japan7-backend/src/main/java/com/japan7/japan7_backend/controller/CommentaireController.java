package com.japan7.japan7_backend.controller;

import com.japan7.japan7_backend.model.Commentaire;
import com.japan7.japan7_backend.repository.CommentaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/commentaires")
public class CommentaireController {

    @Autowired
    private CommentaireRepository commentaireRepository;

    @GetMapping
    public List<Commentaire> getAllCommentaires() {
        return commentaireRepository.findAll();
    }

    @GetMapping("/evenement/{evenementId}")
    public List<Commentaire> getCommentairesParEvenement(@PathVariable Long evenementId) {
        return commentaireRepository.findByEvenementId(evenementId);
    }

    @PostMapping
    public Commentaire ajouterCommentaire(@RequestBody Commentaire commentaire) {
        commentaire.setDate(LocalDateTime.now());
        return commentaireRepository.save(commentaire);
    }

    @DeleteMapping("/{id}")
    public void supprimerCommentaire(@PathVariable Long id) {
        commentaireRepository.deleteById(id);
    }
}
