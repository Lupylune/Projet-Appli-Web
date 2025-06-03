package com.japan7.japan7_backend.repository;

import com.japan7.japan7_backend.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}