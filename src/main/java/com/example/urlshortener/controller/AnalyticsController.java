package com.example.urlshortener.controller;

import com.example.urlshortener.dto.UrlResponse;
import com.example.urlshortener.service.UrlService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/analytics")
@CrossOrigin
public class AnalyticsController {

    private final UrlService urlService;

    public AnalyticsController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<UrlResponse> getAnalytics(
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