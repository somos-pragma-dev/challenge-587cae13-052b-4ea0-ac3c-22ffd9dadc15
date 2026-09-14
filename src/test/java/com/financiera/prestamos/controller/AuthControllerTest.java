package com.financiera.prestamos.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.financiera.prestamos.dto.AuthRequest;
import com.financiera.prestamos.security.JwtTokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    private AuthRequest registroRequest;
    private AuthRequest loginRequest;

    @BeforeEach
    void setUp() {
        registroRequest = new AuthRequest("nuevoUsuario", "password123", "CLIENTE");
        loginRequest = new AuthRequest("admin", "admin123", "ADMIN");
    }

    @Test
    @DisplayName("POST /api/auth/register - Registro exitoso de nuevo usuario")
    void testRegistroUsuario_exito() throws Exception {
        AuthRequest request = new AuthRequest("usuarioTest", "password123", "CLIENTE");

        ResultActions result = mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists())
                .andExpect(jsonPath("$.username").value("usuarioTest"))
                .andExpect(jsonPath("$.roles").value("CLIENTE"));
    }

    @Test
    @DisplayName("POST /api/auth/register - Usuario ya existente retorna error 400")
    void testRegistroUsuario_usuarioExistente() throws Exception {
        AuthRequest request = new AuthRequest("admin", "password123", "ADMIN");

        ResultActions result = mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        result.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").exists());
    }

    @Test
    @DisplayName("POST /api/auth/login - Login exitoso retorna token JWT")
    void testLogin_exito() throws Exception {
        AuthRequest request = new AuthRequest("admin", "admin123", "ADMIN");

        ResultActions result = mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists())
                .andExpect(jsonPath("$.username").value("admin"));
    }

    @Test
    @DisplayName("POST /api/auth/login - Credenciales inválidas retorna error 401")
    void testLogin_credencialesInvalidas() throws Exception {
        AuthRequest request = new AuthRequest("admin", "passwordincorrecto", "ADMIN");

        ResultActions result = mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        result.andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.error").exists());
    }

    @Test
    @DisplayName("POST /api/auth/login - Usuario no encontrado retorna error 401")
    void testLogin_usuarioNoExistente() throws Exception {
        AuthRequest request = new AuthRequest("usuarionoexistente", "password123", "CLIENTE");

        ResultActions result = mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        result.andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("POST /api/auth/register - Request con campos vacíos retorna error 400")
    void testRegistroUsuario_camposVacios() throws Exception {
        AuthRequest request = new AuthRequest("", "password123", "CLIENTE");

        ResultActions result = mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        result.andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Token JWT generado contiene los claims correctos")
    void testToken_generadoContieneClaims() throws Exception {
        AuthRequest request = new AuthRequest("testToken", "password123", "GERENTE");

        ResultActions result = mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        String token = result.andReturn().getResponse().getContentAsString();
        String tokenLimpio = objectMapper.readTree(token).get("token").asText();

        assertNotNull(tokenLimpio, "El token no debe ser null");
        assertTrue(tokenLimpio.length() > 0, "El token debe tener contenido");

        String username = jwtTokenUtil.extractUsername(tokenLimpio);
        assertEquals("testToken", username, "El username en el token debe coincidir");

        String roles = jwtTokenUtil.extractRoles(tokenLimpio);
        assertEquals("GERENTE", roles, "Los roles en el token deben coincidir");
    }
}