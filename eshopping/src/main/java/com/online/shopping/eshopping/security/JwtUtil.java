package com.online.shopping.eshopping.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.MacAlgorithm;
import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {
    private static final String SECRET_KEY = "deepaksp";
    private static final long EXPIRATION_MILLIS = 3600000; // 1 hour
    private final MacAlgorithm algorithm = Jwts.SIG.HS256;
    private final SecretKey key;

    public JwtUtil() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_MILLIS))
                .signWith(key, algorithm)  // ✅ Fix: Use signWith(Key, Algorithm)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(key)  // ✅ Fix: Use verifyWith instead of setSigningKey
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .verifyWith(key)  // ✅ Fix: Use verifyWith
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}
