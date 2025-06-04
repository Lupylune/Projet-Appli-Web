package com.japan7.japan7_backend.repository;

import com.japan7.japan7_backend.model.Bureau;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BureauRepository extends JpaRepository<Bureau, Long> {
    Optional<Bureau> findByPoste(String attr0);
}