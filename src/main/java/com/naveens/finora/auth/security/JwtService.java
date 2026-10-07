package com.naveens.finora.auth.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    private static final String SECRECT_KEY =
            "finora-super-secret-key-that-is-long-enough-for-hs256";

    private static final long EXPIRATION_TIME =
            1000*60*60;

    private SecretKey getSigningKey(){
        return Keys.hmacShaKeyFor(
                SECRECT_KEY.getBytes(StandardCharsets.UTF_8)
        );
    }

    public String generateToken(Long userId, String email){
        Date now = new Date();

        Date expiration = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .subject(userId.toString())
                .claim("email", email)
                .issuedAt(now)
                .expiration(expiration)
                .signWith(getSigningKey())
                .compact();
    }

    public Long extractUserId(String token){
        String subject = Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
        return Long.valueOf(subject);
    }
}
