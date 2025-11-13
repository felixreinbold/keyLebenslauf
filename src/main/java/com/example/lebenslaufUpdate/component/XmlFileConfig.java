package com.example.lebenslaufUpdate.component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class XmlFileConfig {

    @Value("${app.keys-file-path}")
    private String filePath;
    
    
    public File getOrCreateKeysFile() throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            Files.copy(new ClassPathResource("default-keys.xml").getInputStream(), file.toPath());
        } else if (file.length() == 0) {
            // Datei existiert, ist aber leer → Initialstruktur schreiben
            String emptyXml = "<keys></keys>";
            Files.writeString(file.toPath(), emptyXml);
        }
        return file;
    }
    
}
