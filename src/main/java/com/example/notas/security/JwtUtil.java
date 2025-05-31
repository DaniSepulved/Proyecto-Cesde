package com.example.notas.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.notas.model.Estudiantes;
import com.example.notas.model.Profesores;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    @Value("${jwt.expiration}")
    private long EXPIRATION_TIME;

    private Key signingKey;

    // Este método se ejecuta después de inyectar las propiedades
    @PostConstruct
    public void init() {
        // Decodifica la clave secreta y crea el Key para firmar JWT
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        signingKey = Keys.hmacShaKeyFor(keyBytes);

        // Opcional: imprime para verificar carga de propiedades
        System.out.println("JWT SECRET loaded: " + (SECRET_KEY != null));
        System.out.println("JWT EXPIRATION loaded: " + EXPIRATION_TIME);
    }

    // Genera token para estudiante
    public String generateToken(Estudiantes estudiante) {
        return Jwts.builder()
                .setSubject(estudiante.getEmail())
                .claim("role", "ESTUDIANTE")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(signingKey, SignatureAlgorithm.HS256)
                .compact();
    }

    // Genera token para profesor
    public String generateToken(Profesores profesor) {
        return Jwts.builder()
                .setSubject(profesor.getEmail())
                .claim("role", "PROFESOR")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(signingKey, SignatureAlgorithm.HS256)
                .compact();
    }

    // Extrae claims del token
    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Obtiene email del token
    public String getEmailFromToken(String token) {
        return extractClaims(token).getSubject();
    }

    // Obtiene rol del token
    public String getRoleFromToken(String token) {
        return extractClaims(token).get("role", String.class);
    }

    // Valida el token
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            System.err.println("Token validation failed: " + e.getMessage());
            return false;
        }
    }
}
