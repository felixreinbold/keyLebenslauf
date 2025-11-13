package com.example.lebenslaufUpdate.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lebenslaufUpdate.component.XmlFileConfig;
import com.example.lebenslaufUpdate.model.Keys;
import com.example.lebenslaufUpdate.service.KeyService;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {
    

    private final XmlMapper xmlMapper = new XmlMapper();

    @Autowired
    private KeyService keyService;
    
    @Autowired XmlFileConfig xmlFileConfig;

    @GetMapping("/keys")
    public List<String> getKeys() throws IOException {
        
        File xmlFile = xmlFileConfig.getOrCreateKeysFile();
        
        Keys keys = xmlMapper.readValue(xmlFile, Keys.class);
        return keys.getKey();
    }

    @PostMapping("/keys")
    public ResponseEntity<Void> addKey(@RequestBody Map<String, String> request) throws IOException {
        File xmlFile = xmlFileConfig.getOrCreateKeysFile();
       
        Keys keys = xmlMapper.readValue(xmlFile, Keys.class);

        String newKey = request.get("key");
        
        if (newKey == null || newKey.isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        
        if (newKey != null && !keys.getKey().contains(newKey)) {
            keys.getKey().add(newKey);
            xmlMapper.writeValue(xmlFile, keys);
            keyService.refreshKeys();
        }
        

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/keys/{key}")
    public ResponseEntity<Void> deleteKey(@PathVariable String key) throws IOException {
        File xmlFile = xmlFileConfig.getOrCreateKeysFile();
        
        Keys keys = xmlMapper.readValue(xmlFile, Keys.class);

        boolean removed = keys.getKey().removeIf(k -> k.trim().equals(key.trim()));
        xmlMapper.writeValue(xmlFile, keys);
        keyService.refreshKeys();
        if (!removed) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}