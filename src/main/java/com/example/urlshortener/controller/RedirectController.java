package com.example.urlshortener.controller;

import java.net.URI;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.urlshortener.service.UrlService;

@RestController
public class RedirectController {

    private final UrlService urlService;

    public RedirectController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/s/{shortCode}")
    public ResponseEntity<Void> redirect(
            @PathVariable String shortCode) {

        return urlService.getByShortCode(shortCode)
                .map(url -> {

                    urlService.registerClick(url);

                    HttpHeaders headers = new HttpHeaders();
                    headers.setLocation(URI.create(url.getOriginalUrl()));

                    return new ResponseEntity<Void>(
                            headers,
                            HttpStatus.FOUND
                    );
                })
                .orElse(ResponseEntity.notFound().build());
    }
}