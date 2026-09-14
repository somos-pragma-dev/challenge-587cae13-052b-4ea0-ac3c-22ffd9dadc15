package com.financiera.prestamos.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.financiera.prestamos.dto.LoanRequest;
import com.financiera.prestamos.security.JwtTokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class LoanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private String tokenAdmin;
    private String tokenCliente;

    @BeforeEach
    void setUp() {
        tokenAdmin = jwtTokenUtil.generateToken("admin", "ADMIN");
        tokenCliente = jwtTokenUtil.generateToken("cliente1", "CLIENTE");
    }

    @Test
    @DisplayName("GET /api/loans - Sin autenticación retorna 401")
    void testGetLoans_sinAutenticacion() throws Exception {
        ResultActions result = mockMvc.perform(get("/api/loans"));
        result.andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("GET /api/loans - Con token válido de ADMIN retorna todos los préstamos")
    void testGetLoans_conTokenAdmin() throws Exception {
        ResultActions result = mockMvc.perform(get("/api/loans")
                .header("Authorization", "Bearer " + tokenAdmin));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @DisplayName("GET /api/loans - Con token válido de CLIENTE retorna solo sus préstamos")
    void testGetLoans_conTokenCliente() throws Exception {
        ResultActions result = mockMvc.perform(get("/api/loans")
                .header("Authorization", "Bearer " + tokenCliente));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @DisplayName("GET /api/loans/{id} - Préstamo existente retorna detalles completos")
    void testGetLoanById_existente() throws Exception {
        ResultActions result = mockMvc.perform(get("/api/loans/1")
                .header("Authorization", "Bearer " + tokenAdmin));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.monto").exists())
                .andExpect(jsonPath("$.estado").exists());
    }

    @Test
    @DisplayName("GET /api/loans/{id} - Préstamo no existente retorna 404")
    void testGetLoanById_noExistente() throws Exception {
        ResultActions result = mockMvc.perform(get("/api/loans/99999")
                .header("Authorization", "Bearer " + tokenAdmin));

        result.andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST /api/loans - Crear préstamo con token de CLIENTE exitosamente")
    void testCreateLoan_conTokenCliente() throws Exception {
        LoanRequest request = new LoanRequest(
                new BigDecimal("50000"),
                24,
                "Compra de vehículo",
                "PERSONAL"
        );

        ResultActions result = mockMvc.perform(post("/api/loans")
                .header("Authorization", "Bearer " + tokenCliente)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.monto").value(50000))
                .andExpect(jsonPath("$.estado").value("PENDIENTE"));
    }

    @Test
    @DisplayName("POST /api/loans - Crear préstamo con monto inválido retorna 400")
    void testCreateLoan_montoInvalido() throws Exception {
        LoanRequest request = new LoanRequest(
                new BigDecimal("-1000"),
                24,
                "Préstamo inválido",
                "PERSONAL"
        );

        ResultActions result = mockMvc.perform(post("/api/loans")
                .header("Authorization", "Bearer " + tokenCliente)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        result.andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("PUT /api/loans/{id}/approve - ADMIN puede approves préstamos")
    void testApproveLoan_conTokenAdmin() throws Exception {
        ResultActions result = mockMvc.perform(put("/api/loans/1/approve")
                .header("Authorization", "Bearer " + tokenAdmin));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("APROBADO"));
    }

    @Test
    @DisplayName("PUT /api/loans/{id}/approve - CLIENTE no puede approves préstamos")
    void testApproveLoan_sinPermisos() throws Exception {
        ResultActions result = mockMvc.perform(put("/api/loans/1/approve")
                .header("Authorization", "Bearer " + tokenCliente));

        result.andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("PUT /api/loans/{id}/reject - ADMIN puede rechazar préstamos")
    void testRejectLoan_conTokenAdmin() throws Exception {
        ResultActions result = mockMvc.perform(put("/api/loans/2/reject")
                .header("Authorization", "Bearer " + tokenAdmin));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("RECHAZADO"));
    }

    @Test
    @DisplayName("Token JWT extrae correctamente el username del contexto de seguridad")
    void testToken_usernameExtraidoDelContexto() throws Exception {
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                "usuarioPrueba", null, List.of(new SimpleGrantedAuthority("CLIENTE"))
        );
        SecurityContextHolder.getContext().setAuthentication(auth);

        String token = jwtTokenUtil.generateToken("usuarioPrueba", "CLIENTE");
        String usernameExtraido = jwtTokenUtil.extractUsername(token);

        assertEquals("usuarioPrueba", usernameExtraido);
    }

    @Test
    @DisplayName("GET /api/loans - Token malformado retorna 401")
    void testGetLoans_tokenMalformado() throws Exception {
        ResultActions result = mockMvc.perform(get("/api/loans")
                .header("Authorization", "Bearer tokenInvalido"));

        result.andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("POST /api/loans - Plazo mayor al máximo retorna 400")
    void testCreateLoan_plazoExcedido() throws Exception {
        LoanRequest request = new LoanRequest(
                new BigDecimal("100000"),
                120,
                "Plazo excedido",
                "HIPOTECARIO");

        ResultActions result = mockMvc.perform(post("/api/loans")
                .header("Authorization", "Bearer " + tokenCliente)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        result.andExpect(status().isBadRequest());
    }
}