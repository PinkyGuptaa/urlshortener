package com.example.demo.service;

import com.example.demo.model.UrlMapping;
import com.example.demo.repo.UrlMappingRepo;
import com.example.demo.util.Base62Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
    public interface UrlShortenerService {
        UrlMapping createShortUrl(String originalUrl);

        UrlMapping getOriginalUrl(String shortUrl);
    }
