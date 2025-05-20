package com.japan7.japan7_backend.repository;

import com.japan7.japan7_backend.model.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentaireRepository extends JpaRepository<Commentaire, Long> {
    List<Commentaire> findByEvenementId(Long evenementId);
}
