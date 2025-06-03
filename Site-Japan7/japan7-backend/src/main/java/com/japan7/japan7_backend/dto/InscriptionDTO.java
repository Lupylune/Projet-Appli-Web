package com.japan7.japan7_backend.dto;

public class InscriptionDTO {
    private String email;
    private Long evenementId;

    public InscriptionDTO() {}

    public InscriptionDTO(String email, Long evenementId) {
        this.email = email;
        this.evenementId = evenementId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getEvenementId() {
        return evenementId;
    }

    public void setEvenementId(Long evenementId) {
        this.evenementId = evenementId;
    }
}