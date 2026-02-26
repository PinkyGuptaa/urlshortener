package com.example.demo.serviceImpl;

import com.example.demo.model.UrlMapping;
import com.example.demo.repo.UrlMappingRepo;
import com.example.demo.service.UrlShortenerService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;


@Service
public class UrlShortenerServiceImpl implements UrlShortenerService {
    @Autowired
    UrlMappingRepo urlMappingRepo;
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SHORT_URL_LENGTH = 6;
    private final Random random = new Random();

    @Override
    public UrlMapping createShortUrl(String originalUrl) {
        String shortUrl = generateUniqueShortUrl();
        UrlMapping mapping = new UrlMapping();
        mapping.setOriginalUrl(originalUrl);
        mapping.setShortUrl(shortUrl);
        return urlMappingRepo.save(mapping);
    }

    @Override
    public UrlMapping getOriginalUrl(String shortUrl) {
        Optional<UrlMapping> mapping = urlMappingRepo.findByShortUrl(shortUrl);
        return mapping.orElse(null);
    }

    private String generateUniqueShortUrl() {
        String shortUrl;
        do {
            shortUrl = generateRandomString();
        } while (urlMappingRepo.findByShortUrl(shortUrl).isPresent());
        return shortUrl;
    }

    private String generateRandomString() {
        StringBuilder sb = new StringBuilder(SHORT_URL_LENGTH);
        for (int i = 0; i < SHORT_URL_LENGTH; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }
}
