package com.japan7.japan7_backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Evenement {

    public enum TypeEvenement {
        PROJECTION_ANIME,
        KARAOKE,
        ATELIER_CUISINE,
        COURS_JAPONAIS
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private TypeEvenement typeActivite;

    @OneToMany(mappedBy = "evenement", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Inscription> inscriptions = new ArrayList<>();

    @OneToMany(mappedBy = "evenement", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ressource> ressources = new ArrayList<>();

    // Champs spécifiques pour les projections d'anime
    private String animeTitre;
    private String animeSynopsis;
    private String animeImageUrl;
    private int episodeCourant;
    private String animeMalId;

    // Champs spécifiques pour les karaokés
    private String chansonTitre;
    private String chansonArtiste;
    private String chansonSpotifyUrl;

    public Evenement() {}

    public Evenement(String titre, String description, LocalDateTime date, TypeEvenement typeActivite) {
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

    public TypeEvenement getTypeActivite() { return typeActivite; }
    public void setTypeActivite(TypeEvenement typeActivite) { this.typeActivite = typeActivite; }

    public List<Inscription> getInscriptions() { return inscriptions; }
    public void setInscriptions(List<Inscription> inscriptions) { this.inscriptions = inscriptions; }

    public List<Ressource> getRessources() { return ressources; }
    public void setRessources(List<Ressource> ressources) { this.ressources = ressources; }

    // Anime
    public String getAnimeTitre() { return animeTitre; }
    public void setAnimeTitre(String animeTitre) { this.animeTitre = animeTitre; }

    public String getAnimeSynopsis() { return animeSynopsis; }
    public void setAnimeSynopsis(String animeSynopsis) { this.animeSynopsis = animeSynopsis; }

    public String getAnimeImageUrl() { return animeImageUrl; }
    public void setAnimeImageUrl(String animeImageUrl) { this.animeImageUrl = animeImageUrl; }

    public int getEpisodeCourant() { return episodeCourant; }
    public void setEpisodeCourant(int episodeCourant) { this.episodeCourant = episodeCourant; }

    public String getAnimeMalId() { return animeMalId; }
    public void setAnimeMalId(String animeMalId) { this.animeMalId = animeMalId; }

    // Karaoké
    public String getChansonTitre() { return chansonTitre; }
    public void setChansonTitre(String chansonTitre) { this.chansonTitre = chansonTitre; }

    public String getChansonArtiste() { return chansonArtiste; }
    public void setChansonArtiste(String chansonArtiste) { this.chansonArtiste = chansonArtiste; }

    public String getChansonSpotifyUrl() { return chansonSpotifyUrl; }
    public void setChansonSpotifyUrl(String chansonSpotifyUrl) { this.chansonSpotifyUrl = chansonSpotifyUrl; }
}
