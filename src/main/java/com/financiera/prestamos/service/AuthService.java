package com.financiera.prestamos.service;

import com.financiera.prestamos.dto.AuthRequest;
import com.financiera.prestamos.dto.AuthResponse;
import com.financiera.prestamos.exception.UserAlreadyExistsException;
import com.financiera.prestamos.model.User;
import com.financiera.prestamos.repository.UserRepository;
import com.financiera.prestamos.security.JwtTokenUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository, JwtTokenUtil jwtTokenUtil,
                       PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtTokenUtil = jwtTokenUtil;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse register(AuthRequest request) {
        if (userRepository.existsByUsername(request.username())) {
            throw new UserAlreadyExistsException("El usuario ya existe: " + request.username());
        }

        String encodedPassword = passwordEncoder.encode(request.password());
        Set<String> roles = request.roles() != null ? request.roles() : Set.of("ROLE_USER");

        User user = new User();
        user.setUsername(request.username());
        user.setPassword(encodedPassword);
        user.setRoles(roles);
        user.setEmail(request.email());

        User savedUser = userRepository.save(user);

        String token = jwtTokenUtil.generateToken(savedUser.getUsername(), 
                savedUser.getRoles().stream().toList(),
                savedUser.getId());

        return AuthResponse.success(token, savedUser.getUsername(),
                String.join(",", savedUser.getRoles()), jwtTokenUtil.getExpiration());
    }

    public AuthResponse login(AuthRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.username(), request.password())
            );
        } catch (Exception e) {
            throw new BadCredentialsException("Credenciales inválidas");
        }

        User user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new BadCredentialsException("Usuario no encontrado"));

        String token = jwtTokenUtil.generateToken(user.getUsername(), 
                user.getRoles().stream().toList(),
                user.getId());

        return AuthResponse.success(token, user.getUsername(),
                String.join(",", user.getRoles()), jwtTokenUtil.getExpiration());
    }

    public boolean validateToken(String token) {
        try {
            return jwtTokenUtil.validateToken(token);
        } catch (Exception e) {
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        return jwtTokenUtil.extractUsername(token);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}