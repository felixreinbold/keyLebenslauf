package com.example.lebenslaufUpdate.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.example.lebenslaufUpdate.component.XmlFileConfig;
import com.example.lebenslaufUpdate.model.Keys;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import jakarta.annotation.PostConstruct;

@Service
public class KeyService {

    private List<String> cachedKeys = new ArrayList<>();

    
    @Autowired
    XmlFileConfig xmlFileConfig;
    
    public void refreshKeys() {
        try {
            File xmlFile = xmlFileConfig.getOrCreateKeysFile();
            Keys keys = new XmlMapper().readValue(xmlFile, Keys.class);
            cachedKeys = keys.getKey();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isValidKey(String key) {
        return cachedKeys.contains(key);
    }

    @PostConstruct
    public void init() {
        refreshKeys();
    }
}
