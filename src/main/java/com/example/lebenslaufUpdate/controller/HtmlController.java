package com.example.lebenslaufUpdate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HtmlController {

    @GetMapping("/protected/admin.html")
    public String adminView() {
        return "forward:/protected/admin-view.html"; // wird erst nach Filter geladen
    }
}