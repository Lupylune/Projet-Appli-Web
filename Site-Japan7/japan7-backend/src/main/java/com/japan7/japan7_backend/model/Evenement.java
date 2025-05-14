package com.japan7.japan7_backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Evenement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "evenement", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Inscription> inscriptions = new ArrayList<>();


    private String titre;
    private String description;
    private LocalDateTime date;
    private String typeActivite;
    @OneToMany(mappedBy = "evenement", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ressource> ressources = new ArrayList<>();


    public Evenement() {}

    public Evenement(String titre, String description, LocalDateTime date, String typeActivite) {
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.typeActivite = typeActivite;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public String getTypeActivite() { return typeActivite; }
    public void setTypeActivite(String typeActivite) { this.typeActivite = typeActivite; }

    public List<Ressource> getRessources() {
        return ressources;
    }

    public void setRessources(List<Ressource> ressources) {
        this.ressources = ressources;
    }

}
