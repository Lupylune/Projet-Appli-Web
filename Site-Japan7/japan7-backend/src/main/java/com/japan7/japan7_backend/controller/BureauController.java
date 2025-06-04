package com.japan7.japan7_backend.controller;

import com.japan7.japan7_backend.dto.BureauDTO;
import com.japan7.japan7_backend.model.Bureau;
import com.japan7.japan7_backend.model.Membre;
import com.japan7.japan7_backend.repository.BureauRepository;
import com.japan7.japan7_backend.repository.MembreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bureau")
@CrossOrigin(origins = "*")
public class BureauController {

    @Autowired
    private BureauRepository bureauRepository;

    @Autowired
    private MembreRepository membreRepository;

    @GetMapping
    public List<Bureau> getAll() {
        return bureauRepository.findAll();
    }

    @PostMapping("/create_update")
    public ResponseEntity<?> createOrUpdateBureau(@RequestBody BureauDTO bureauDto) {
        Optional<Bureau> optionalBureau = bureauRepository.findByPoste(bureauDto.getRole());

        Bureau bureau = optionalBureau.orElse(new Bureau());
        bureau.setPoste(bureauDto.getRole());

        Membre membre = membreRepository.findById(bureauDto.getMembreId())
                .orElseThrow(() -> new RuntimeException("Membre not found with id " + bureauDto.getMembreId()));

        bureau.setMembre(membre);

        bureauRepository.save(bureau);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Bureau bureau) {
        Bureau existing = bureauRepository.findById(id).orElse(null);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }

        Membre membre = membreRepository.findById(bureau.getMembre().getId()).orElse(null);
        if (membre == null) {
            return ResponseEntity.badRequest().body("Membre introuvable.");
        }

        existing.setPoste(bureau.getPoste());
        existing.setMembre(membre);
        return ResponseEntity.ok(bureauRepository.save(existing));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bureauRepository.deleteById(id);
    }
}