package com.japan7.japan7_backend.dto;

import com.japan7.japan7_backend.model.Commentaire;
import com.japan7.japan7_backend.model.Membre;

import java.time.LocalDateTime;

public class BureauDTO {
    private Long membreId;
    private String role;

    // Getters and setters
    public Long getMembreId() { return membreId; }
    public void setMembreId(Long membreId) { this.membreId = membreId; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}