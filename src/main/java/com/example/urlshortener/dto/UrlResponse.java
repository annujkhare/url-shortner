package com.example.urlshortener.dto;

import java.time.LocalDateTime;

import com.example.urlshortener.entity.Url;

public class UrlResponse {

    private String originalUrl;
    private String shortCode;
    private String shortUrl;
    private Long clicks;
    private LocalDateTime createdAt;

    public UrlResponse(Url url, String baseUrl) {
        this.originalUrl = url.getOriginalUrl();
        this.shortCode = url.getShortCode();
        this.shortUrl = baseUrl + "/s/" + url.getShortCode();
        this.clicks = url.getClicks();
        this.createdAt = url.getCreatedAt();
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public String getShortCode() {
        return shortCode;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public Long getClicks() {
        return clicks;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}