package com.japan7.japan7_backend.repository;

import com.japan7.japan7_backend.model.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InscriptionRepository extends JpaRepository<Inscription, Long> {
    List<Inscription> findByEvenementId(Long evenementId);

    boolean existsByMembreIdAndEvenementId(Long membreId, Long evenementId);

    void deleteByMembreIdAndEvenementId(Long membreId, Long evenementId);
}


