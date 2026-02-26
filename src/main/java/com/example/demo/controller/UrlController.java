package com.example.demo.controller;

import com.example.demo.model.UrlMapping;
import com.example.demo.service.UrlShortenerService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/url")
@CrossOrigin("*")
public class UrlController {
    @Autowired
    UrlShortenerService urlShortenerService;
    @PostMapping("/shorten")
    public UrlMapping shortenUrl(@RequestParam String originalUrl) {
        return urlShortenerService.createShortUrl(originalUrl);
    }

    @GetMapping("/original/{shortUrl}")
    public UrlMapping getOriginalUrl(@PathVariable String shortUrl) {
        return urlShortenerService.getOriginalUrl(shortUrl);
    }
}





