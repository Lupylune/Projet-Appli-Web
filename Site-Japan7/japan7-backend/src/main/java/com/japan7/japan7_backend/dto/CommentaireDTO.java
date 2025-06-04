package com.japan7.japan7_backend.dto;

import com.japan7.japan7_backend.model.Commentaire;
import com.japan7.japan7_backend.model.Membre;

import java.time.LocalDateTime;

public class CommentaireDTO {
    public Long id;
    public Membre auteur;
    public String contenu;
    public LocalDateTime date;
    public Long evenementId;
    public String evenementTitre;

    public CommentaireDTO(Commentaire c) {
        this.id = c.getId();
        this.auteur = c.getAuteur();
        this.contenu = c.getContenu();
        this.date = c.getDate();

        if (c.getEvenement() != null) {
            this.evenementId = c.getEvenement().getId();
            this.evenementTitre = c.getEvenement().getTitre();
        } else {
            this.evenementId = null;
            this.evenementTitre = "Événement supprimé";
        }
    }
}
