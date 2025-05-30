package com.japan7.japan7_backend.controller;

import com.japan7.japan7_backend.model.Evenement;
import com.japan7.japan7_backend.repository.EvenementRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/evenements")
@CrossOrigin(origins = "*") // Autorise les appels depuis le frontend local
public class EvenementController {

    private final EvenementRepository repo;

    public EvenementController(EvenementRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Evenement> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Evenement> getById(@PathVariable Long id) {
        return repo.findById(id);
    }

    @PostMapping
    public Evenement create(@RequestBody Evenement evt) {
        return repo.save(evt);
    }

    @PutMapping("/{id}")
    public Evenement update(@PathVariable Long id, @RequestBody Evenement evt) {
        System.out.println("🔎 Modification de l'événement id = " + id + ", evt.id = " + evt.getId());

        // On impose que l’ID transmis dans le path et l’objet soient cohérents
        if (evt.getId() != null && !evt.getId().equals(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "L’ID de l’URL et du corps ne correspondent pas");
        }

        evt.setId(id);

        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Événement non trouvé");
        }

        return repo.save(evt);
    }




    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}