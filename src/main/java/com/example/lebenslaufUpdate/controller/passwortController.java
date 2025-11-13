package com.example.lebenslaufUpdate.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lebenslaufUpdate.model.Passwort;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/admin")
public class passwortController {
    private static final String ADMIN_PASSWORT = "admin123";

    @PostMapping
    public ResponseEntity<?> verifyAdmin(@RequestBody Passwort request, HttpServletRequest httpRequest) {
        if (ADMIN_PASSWORT.equals(request.getPasswort())) {
            httpRequest.getSession(true).setAttribute("admin", true);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(403).body("Zugang verweigert");
        }
    }
}