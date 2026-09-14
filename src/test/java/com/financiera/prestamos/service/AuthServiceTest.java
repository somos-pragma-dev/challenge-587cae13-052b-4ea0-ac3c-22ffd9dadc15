package com.financiera.prestamos.service;

import com.financiera.prestamos.dto.AuthRequest;
import com.financiera.prestamos.dto.AuthResponse;
import com.financiera.prestamos.exception.UserAlreadyExistsException;
import com.financiera.prestamos.model.User;
import com.financiera.prestamos.repository.UserRepository;
import com.financiera.prestamos.security.JwtTokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas unitarias para AuthService")
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthService authService;

    private AuthRequest registroRequest;
    private AuthRequest loginRequest;
    private User usuarioExistente;
    private User nuevoUsuario;

    @BeforeEach
    void setUp() {
        registroRequest = new AuthRequest("nuevoUsuario", "password123", "CLIENTE");
        loginRequest = new AuthRequest("usuarioExistente", "password123", "CLIENTE");
        
        usuarioExistente = new User();
        usuarioExistente.setUsername("usuarioExistente");
        usuarioExistente.setPassword("hashedPassword");
        usuarioExistente.setRole("CLIENTE");
        usuarioExistente.setActive(true);
        
        nuevoUsuario = new User();
        nuevoUsuario.setUsername("nuevoUsuario");
        nuevoUsuario.setPassword("hashedPassword");
        nuevoUsuario.setRole("CLIENTE");
        nuevoUsuario.setActive(true);
    }

    @Test
    @DisplayName("Registro exitoso de usuario retorna token JWT")
    void registro_exitoso_retorna_token() {
        when(userRepository.findByUsername("nuevoUsuario")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("password123")).thenReturn("hashedPassword");
        when(userRepository.save(any(User.class))).thenReturn(nuevoUsuario);
        when(jwtTokenUtil.generateToken("nuevoUsuario", "CLIENTE")).thenReturn("jwt.token.signature");
        when(jwtTokenUtil.getExpirationMs()).thenReturn(3600000L);

        AuthResponse response = authService.register(registroRequest);

        assertNotNull(response);
        assertEquals("jwt.token.signature", response.token());
        assertEquals("nuevoUsuario", response.username());
        assertEquals("CLIENTE", response.roles());
        verify(userRepository).save(any(User.class));
        verify(jwtTokenUtil).generateToken("nuevoUsuario", "CLIENTE");
    }

    @Test
    @DisplayName("Registro de usuario existente lanza excepción")
    void registro_usuario_existente_lanza_excepcion() {
        when(userRepository.findByUsername("usuarioExistente")).thenReturn(Optional.of(usuarioExistente));

        assertThrows(UserAlreadyExistsException.class, () -> authService.register(loginRequest));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    @DisplayName("Login con credenciales válidas retorna token JWT")
    void login_credenciales_validas_retorna_token() {
        when(userRepository.findByUsername("usuarioExistente")).thenReturn(Optional.of(usuarioExistente));
        when(passwordEncoder.matches("password123", "hashedPassword")).thenReturn(true);
        when(jwtTokenUtil.generateToken("usuarioExistente", "CLIENTE")).thenReturn("jwt.token.signature");
        when(jwtTokenUtil.getExpirationMs()).thenReturn(3600000L);

        AuthResponse response = authService.authenticate(loginRequest);

        assertNotNull(response);
        assertEquals("jwt.token.signature", response.token());
        assertEquals("usuarioExistente", response.username());
        assertEquals("CLIENTE", response.roles());
    }

    @Test
    @DisplayName("Login con usuario inexistente lanza excepción")
    void login_usuario_inexistente_lanza_excepcion() {
        when(userRepository.findByUsername("inexistente")).thenReturn(Optional.empty());

        AuthRequest request = new AuthRequest("inexistente", "password123", "CLIENTE");
        assertThrows(BadCredentialsException.class, () -> authService.authenticate(request));
    }

    @Test
    @DisplayName("Login con contraseña incorrecta lanza excepción")
    void login_password_incorrecto_lanza_excepcion() {
        when(userRepository.findByUsername("usuarioExistente")).thenReturn(Optional.of(usuarioExistente));
        when(passwordEncoder.matches("passwordWrong", "hashedPassword")).thenReturn(false);

        AuthRequest request = new AuthRequest("usuarioExistente", "passwordWrong", "CLIENTE");
        assertThrows(BadCredentialsException.class, () -> authService.authenticate(request));
    }

    @Test
    @DisplayName("Login con usuario inactivo lanza excepción")
    void login_usuario_inactivo_lanza_excepcion() {
        usuarioExistente.setActive(false);
        when(userRepository.findByUsername("usuarioExistente")).thenReturn(Optional.of(usuarioExistente));
        when(passwordEncoder.matches("password123", "hashedPassword")).thenReturn(true);

        AuthRequest request = new AuthRequest("usuarioExistente", "password123", "CLIENTE");
        assertThrows(BadCredentialsException.class, () -> authService.authenticate(request));
    }

    @Test
    @DisplayName("Registro con rol nulo usa rol por defecto CLIENTE")
    void registro_rol_nulo_usa_default() {
        AuthRequest requestSinRol = new AuthRequest("usuarioNuevo", "password123", null);
        when(userRepository.findByUsername("usuarioNuevo")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("password123")).thenReturn("hashedPassword");
        when(userRepository.save(any(User.class))).thenAnswer(inv -> inv.getArgument(0));
        when(jwtTokenUtil.generateToken(anyString(), eq("CLIENTE"))).thenReturn("token");
        when(jwtTokenUtil.getExpirationMs()).thenReturn(3600000L);

        AuthResponse response = authService.register(requestSinRol);

        assertNotNull(response);
        assertEquals("CLIENTE", response.roles());
        verify(jwtTokenUtil).generateToken("usuarioNuevo", "CLIENTE");
    }

    @Test
    @DisplayName("Token JWT contiene información correcta del usuario")
    void token_contiene_info_correcta() {
        when(userRepository.findByUsername("usuarioExistente")).thenReturn(Optional.of(usuarioExistente));
        when(passwordEncoder.matches("password123", "hashedPassword")).thenReturn(true);
        when(jwtTokenUtil.generateToken("usuarioExistente", "CLIENTE")).thenReturn("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c3VhcmlvRXhpc3RlbnRlIiwiZXhwIjoxNzA0MDgwMDAwfQ.signature");
        when(jwtTokenUtil.getExpirationMs()).thenReturn(3600000L);

        AuthResponse response = authService.authenticate(loginRequest);

        assertTrue(response.token().contains("eyJ"));
        assertTrue(response.expirationMs() > System.currentTimeMillis());
    }
}