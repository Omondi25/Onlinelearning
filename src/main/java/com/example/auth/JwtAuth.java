package com.example.auth;



import org.springframework.stereotype.Component;

import java.util.Date;

    @Component
    public class JwtAuth {

        private String secret = "mySecretKey"; // Use environment variable in production

        public String generateToken(String username) {
            return Jwts.builder()
                    .setSubject(username)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10-hour expiry
                    .signWith(SignatureAlgorithm.HS256, secret)
                    .compact();
        }

        public Claims extractClaims(String token) {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        }

        public String extractUsername(String token) {
            return extractClaims(token).getSubject();
        }

        public boolean isTokenExpired(String token) {
            return extractClaims(token).getExpiration().before(new Date());
        }

        public boolean validateToken(String token, String username) {
            return (username.equals(extractUsername(token)) && !isTokenExpired(token));
        }
    }


