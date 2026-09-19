package com.example.urlshortener.controller;

import com.example.urlshortener.dto.UrlRequest;
import com.example.urlshortener.dto.UrlResponse;
import com.example.urlshortener.entity.Url;
import com.example.urlshortener.service.UrlService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/urls")
@CrossOrigin
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/shorten")
    public ResponseEntity<UrlResponse> shortenUrl(
            @Valid @RequestBody UrlRequest request,
            HttpServletRequest httpRequest) {

        Url url = urlService.createShortUrl(request.getOriginalUrl());

        String baseUrl = httpRequest.getScheme()
                + "://"
                + httpRequest.getServerName()
                + ":"
                + httpRequest.getServerPort();

        return ResponseEntity.ok(
                new UrlResponse(url, baseUrl)
        );
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<UrlResponse> getUrl(
            @PathVariable String shortCode,
            HttpServletRequest request) {

        return urlService.getByShortCode(shortCode)
                .map(url -> {

                    String baseUrl = request.getScheme()
                            + "://"
                            + request.getServerName()
                            + ":"
                            + request.getServerPort();

                    return ResponseEntity.ok(
                            new UrlResponse(url, baseUrl)
                    );
                })
                .orElse(ResponseEntity.notFound().build());
    }
}