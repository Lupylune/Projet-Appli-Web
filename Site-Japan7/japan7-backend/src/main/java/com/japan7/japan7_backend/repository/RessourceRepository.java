package com.japan7.japan7_backend.repository;

import com.japan7.japan7_backend.model.Ressource;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RessourceRepository extends JpaRepository<Ressource, Long> {
    List<Ressource> findByEvenementId(Long evenementId);
}
