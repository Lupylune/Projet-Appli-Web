package com.japan7.japan7_backend.controller;

import com.japan7.japan7_backend.model.Transaction;
import com.japan7.japan7_backend.model.TypeTransaction;
import com.japan7.japan7_backend.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin(origins = "*")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping
    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }

    @GetMapping("/solde")
    public double getSolde() {
        return transactionRepository.findAll().stream()
                .mapToDouble(t -> t.getType() == TypeTransaction.RECETTE ? t.getMontant() : -t.getMontant())
                .sum();
    }


    @PostMapping
    public Transaction create(@RequestBody Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @PutMapping("/{id}")
    public Transaction update(@PathVariable Long id, @RequestBody Transaction transaction) {
        Transaction existing = transactionRepository.findById(id).orElseThrow();
        existing.setNom(transaction.getNom());
        existing.setType(transaction.getType());
        existing.setMontant(transaction.getMontant());
        return transactionRepository.save(existing);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        transactionRepository.deleteById(id);
    }
}