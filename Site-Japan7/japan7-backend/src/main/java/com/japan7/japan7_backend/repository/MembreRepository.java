package com.japan7.japan7_backend.repository;

import com.japan7.japan7_backend.model.Membre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MembreRepository extends JpaRepository<Membre, Long> {
    Optional<Membre> findByEmail(String email);

    Optional<Membre> findById(Long id);
}
