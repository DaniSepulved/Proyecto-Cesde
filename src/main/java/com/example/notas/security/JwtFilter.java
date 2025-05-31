package com.example.notas.security;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.notas.model.Estudiantes;
import com.example.notas.model.Profesores;
import com.example.notas.repository.EstudiantesRepository;
import com.example.notas.repository.ProfesoresRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private EstudiantesRepository estudiantesRepository;

    @Autowired
    private ProfesoresRepository profesoresRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            if (jwtUtil.validateToken(token)) {
                String email = jwtUtil.getEmailFromToken(token);
                String role = jwtUtil.getRoleFromToken(token);

                Object principal = null;
                String userRole = null;

                if ("ESTUDIANTE".equals(role)) {
                    Optional<Estudiantes> optionalEstudiante = estudiantesRepository.findByEmail(email);
                    if (optionalEstudiante.isPresent()) {
                        principal = optionalEstudiante.get();
                        userRole = "ESTUDIANTE";
                    }
                } else if ("PROFESOR".equals(role)) {
                    Optional<Profesores> optionalProfesor = profesoresRepository.findByEmail(email);
                    if (optionalProfesor.isPresent()) {
                        principal = optionalProfesor.get();
                        userRole = "PROFESOR";
                    }
                } else if ("ADMIN".equals(role)) {
                    // Para ADMIN solo se usa el email como principal (o cualquier otro objeto que prefieras)
                    principal = email;
                    userRole = "ADMIN";
                }

                if (principal != null && userRole != null) {
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    principal,
                                    null,
                                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + userRole))
                            );
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}

