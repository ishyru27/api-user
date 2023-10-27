package com.nisum.latam.components;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.nisum.latam.entities.User;

import java.util.Date;

@Component
public class JwtTokenGenerator {
    @Value("${app.jwtSecret}")
    private String secretKey;
    private long expirationTimeInMs = 3600000;

    public String generateAccessToken(User user) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + expirationTimeInMs);

        String token = Jwts.builder()
                .setSubject(user.getId().toString())
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        return token;
    }
}
