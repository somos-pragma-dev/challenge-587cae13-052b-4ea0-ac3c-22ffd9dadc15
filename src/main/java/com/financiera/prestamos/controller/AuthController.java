package com.financiera.prestamos.controller;

import com.financiera.prestamos.dto.AuthRequest;
import com.financiera.prestamos.dto.AuthResponse;
import com.financiera.prestamos.exception.UserAlreadyExistsException;
import com.financiera.prestamos.security.JwtTokenUtil;
import com.financiera.prestamos.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final AuthenticationManager authenticationManager;
    private final AuthService authService;
    private final UserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, AuthService authService,
                          UserDetailsService userDetailsService, JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.authService = authService;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest request) {
        logger.info("Intento de registro para usuario: {}", request.username());
        try {
            var result = authService.register(request.username(), request.password());
            logger.info("Usuario registrado exitosamente: {}", request.username());
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (UserAlreadyExistsException e) {
            logger.warn("Registro fallido - usuario ya existe: {}", request.username());
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        logger.info("Intento de autenticación para usuario: {}", request.username());
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.username(), request.password())
            );

            UserDetails userDetails = userDetailsService.loadUserByUsername(request.username());
            var user = authService.findByUsername(request.username())
                    .orElseThrow(() -> new BadCredentialsException("Usuario no encontrado"));

            String token = jwtTokenUtil.generateToken(
                    userDetails.getUsername(),
                    userDetails.getAuthorities().stream()
                            .map(auth -> auth.getAuthority().replace("ROLE_", ""))
                            .toList(),
                    user.getId()
            );

            logger.info("Autenticación exitosa para usuario: {}", request.username());
            return ResponseEntity.ok(AuthResponse.success(token, userDetails.getUsername(),
                    userDetails.getAuthorities().toString(), jwtTokenUtil.getExpiration()));
        } catch (BadCredentialsException e) {
            logger.warn("Autenticación fallida para usuario: {}", request.username());
            Map<String, String> error = new HashMap<>();
            error.put("error", "Credenciales inválidas");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
    }
}