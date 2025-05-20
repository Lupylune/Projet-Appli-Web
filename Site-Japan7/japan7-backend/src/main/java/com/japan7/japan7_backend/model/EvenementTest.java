package com.japan7.japan7_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class EvenementTest {

    @Id
    @GeneratedValue
    private Long id;

    private String titre;
    private String description;
    private LocalDateTime date;
    private String typeActivite;

    public EvenementTest() {}

    public EvenementTest(String titre, String description, LocalDateTime date, String typeActivite) {
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.typeActivite = typeActivite;
    }

    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getTypeActivite() {
        return typeActivite;
    }

    public void setTypeActivite(String typeActivite) {
        this.typeActivite = typeActivite;
    }
}
