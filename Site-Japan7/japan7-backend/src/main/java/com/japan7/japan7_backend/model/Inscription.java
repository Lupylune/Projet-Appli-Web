package com.japan7.japan7_backend.model;

import jakarta.persistence.*;

@Entity
public class Inscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Membre membre;

    @ManyToOne
    private Evenement evenement;

    public Inscription() {}

    public Inscription(Membre membre, Evenement evenement) {
        this.membre = membre;
        this.evenement = evenement;
    }

    // Getters & setters
    public Long getId() { return id; }

    public Membre getMembre() { return membre; }
    public void setMembre(Membre membre) { this.membre = membre; }

    public Evenement getEvenement() { return evenement; }
    public void setEvenement(Evenement evenement) { this.evenement = evenement; }
}
