package com.japan7.japan7_backend.controller;

import com.japan7.japan7_backend.model.Membre;
import com.japan7.japan7_backend.repository.MembreRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class LoginController {

    private final MembreRepository membreRepository;

    public LoginController(MembreRepository membreRepository) {
        this.membreRepository = membreRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpSession session) {
        Optional<Membre> membreOpt = membreRepository.findByEmail(request.email());

        if (membreOpt.isPresent()) {
            Membre membre = membreOpt.get();

            if (membre.verifyPassword(request.password())) {
                session.setAttribute("membre", membre.getId());
                session.setAttribute("admin", membre.isAdmin());
                return ResponseEntity.ok().build();
            }
        }

        return ResponseEntity.status(401).body("Email ou mot de passe incorrect");
    }

    @PostMapping("/logout")
    public void logout(HttpSession session) {
        session.invalidate();
    }

    @GetMapping("/me")
    public ResponseEntity<?> me(HttpSession session) {
        Object membreId = session.getAttribute("membre");
        Object admin = session.getAttribute("admin");
        if (membreId != null) {
            return ResponseEntity.ok(membreId);
        }
        return ResponseEntity.status(401).build();
    }

    @GetMapping("/admin")
    public ResponseEntity<?> admin(HttpSession session) {
        Object admin = session.getAttribute("admin");
        if (admin != null) {
            return ResponseEntity.ok(admin);
        }
        return ResponseEntity.status(401).build();
    }

    public record LoginRequest(String email, String password) {}
}