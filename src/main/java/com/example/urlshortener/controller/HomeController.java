package com.example.urlshortener.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/", "/index.html"})
    public ResponseEntity<Resource> home() {
        Resource resource = new ClassPathResource("static/index.html");

        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .body(resource);
    }

    @GetMapping("/style.css")
    public ResponseEntity<Resource> css() {
        Resource resource = new ClassPathResource("static/style.css");

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("text/css"))
                .body(resource);
    }

    @GetMapping("/script.js")
    public ResponseEntity<Resource> js() {
        Resource resource = new ClassPathResource("static/script.js");

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/javascript"))
                .body(resource);
    }
}