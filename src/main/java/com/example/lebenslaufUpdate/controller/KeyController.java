package com.example.lebenslaufUpdate.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lebenslaufUpdate.service.KeyService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class KeyController {

    @Autowired
    private KeyService keyService;

    @PostMapping("/verify-key")
    public ResponseEntity<?> verifyKey(@RequestBody Map<String, String> request, HttpServletRequest httpRequest) {
        String key = request.get("key");

        if (key == null || key.isBlank()) {
            return ResponseEntity.badRequest().body(Map.of(
                    "access", false,
                    "message", "Kein Schlüssel angegeben"
            ));
        }

        if (keyService.isValidKey(key)) {
            // Session setzen
            httpRequest.getSession(true).setAttribute("userKey", key);
            return ResponseEntity.ok(Map.of("access", true));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                    "access", false,
                    "message", "Ungültiger Schlüssel"
            ));
        }
    }
}