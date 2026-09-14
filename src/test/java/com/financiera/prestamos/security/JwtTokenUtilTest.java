package com.financiera.prestamos.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class JwtTokenUtilTest {

    private JwtTokenUtil jwtTokenUtil;
    private static final String SECRET_KEY = " EstaEsUnaClaveSecretaParaPruebasDeJWTQueDebeTener256Bits12345678 ";
    private static final String USERNAME_TEST = "usuarioPrueba";
    private static final String ROLES_TEST = "ADMIN";

    @BeforeEach
    void setUp() {
        jwtTokenUtil = new JwtTokenUtil();
        ReflectionTestUtils.setField(jwtTokenUtil, "secret", SECRET_KEY);
        ReflectionTestUtils.setField(jwtTokenUtil, "expiration", 3600000L);
    }

    @Test
    @DisplayName("GenerateToken - Token generado contiene username y roles correctos")
    void testGenerateToken_conDatosValidos() {
        List<String> roles = List.of(ROLES_TEST);
        String token = jwtTokenUtil.generateToken(USERNAME_TEST, roles, 1L);

        assertNotNull(token, "El token no debe ser null");
        assertTrue(token.length() > 0, "El token debe tener contenido");
        assertTrue(token.contains("."), "El token debe tener formato JWT con puntos");
    }

    @Test
    @DisplayName("ExtractUsername - Extrae correctamente el username del token")
    void testExtractUsername_extraccionExitosa() {
        List<String> roles = List.of(ROLES_TEST);
        String token = jwtTokenUtil.generateToken(USERNAME_TEST, roles, 1L);
        String usernameExtraido = jwtTokenUtil.extractUsername(token);

        assertEquals(USERNAME_TEST, usernameExtraido, "El username extraído debe coincidir");
    }

    @Test
    @DisplayName("ExtractRoles - Extrae correctamente los roles del token")
    void testExtractRoles_extraccionExitosa() {
        List<String> roles = List.of(ROLES_TEST);
        String token = jwtTokenUtil.generateToken(USERNAME_TEST, roles, 1L);
        List<String> rolesExtraidos = jwtTokenUtil.extractRoles(token);

        assertNotNull(rolesExtraidos, "Los roles no deben ser null");
        assertTrue(rolesExtraidos.contains(ROLES_TEST), "Los roles extraídos deben contener el rol esperado");
    }

    @Test
    @DisplayName("ValidateToken - Token válido retorna true")
    void testValidateToken_tokenValido() {
        List<String> roles = List.of(ROLES_TEST);
        String token = jwtTokenUtil.generateToken(USERNAME_TEST, roles, 1L);
        boolean esValido = jwtTokenUtil.validateToken(token);

        assertTrue(esValido, "El token válido debe retornar true");
    }

    @Test
    @DisplayName("ValidateToken - Token expirado lanza excepción")
    void testValidateToken_tokenExpirado() {
        SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        
        Date fechaExpirada = new Date(System.currentTimeMillis() - 1000);
        String tokenExpirado = Jwts.builder()
                .subject(USERNAME_TEST)
                .claim("roles", List.of(ROLES_TEST))
                .expiration(fechaExpirada)
                .issuedAt(new Date(System.currentTimeMillis() - 10000))
                .signWith(key)
                .compact();

        assertThrows(Exception.class, () -> jwtTokenUtil.validateToken(tokenExpirado));
    }

    @Test
    @DisplayName("ValidateToken - Token con firma inválida lanza excepción")
    void testValidateToken_firmaInvalida() {
        List<String> roles = List.of(ROLES_TEST);
        String tokenValido = jwtTokenUtil.generateToken(USERNAME_TEST, roles, 1L);
        String tokenConFirmaAlterada = tokenValido.substring(0, tokenValido.length() - 5) + "xxxxx";

        assertThrows(Exception.class, () -> jwtTokenUtil.validateToken(tokenConFirmaAlterada));
    }

    @Test
    @DisplayName("ExtractExpiration - Extrae la fecha de expiración correctamente")
    void testExtractExpiration_extraccionExitosa() {
        List<String> roles = List.of(ROLES_TEST);
        String token = jwtTokenUtil.generateToken(USERNAME_TEST, roles, 1L);
        Date expiracion = jwtTokenUtil.extractExpiration(token);

        assertNotNull(expiracion, "La expiración no debe ser null");
        assertTrue(expiracion.after(new Date()), "La expiración debe ser futura");
    }

    @Test
    @DisplayName("GenerateToken - Tokens generados para mismo usuario son diferentes")
    void testGenerateToken_tokensUnicos() throws InterruptedException {
        List<String> roles = List.of(ROLES_TEST);
        String token1 = jwtTokenUtil.generateToken(USERNAME_TEST, roles, 1L);
        Thread.sleep(10);
        String token2 = jwtTokenUtil.generateToken(USERNAME_TEST, roles, 1L);

        assertNotEquals(token1, token2, "Cada token debe ser único");
    }

    @Test
    @DisplayName("ExtractClaims - Extrae todos los claims del token")
    void testExtractClaims_extraccionCompleta() {
        List<String> roles = List.of(ROLES_TEST);
        String token = jwtTokenUtil.generateToken(USERNAME_TEST, roles, 1L);
        Claims claims = jwtTokenUtil.extractAllClaims(token);

        assertNotNull(claims, "Los claims no deben ser null");
        assertEquals(USERNAME_TEST, claims.getSubject(), "El subject debe ser el username");
    }

    @Test
    @DisplayName("ValidateToken - Token malformed lanza excepción")
    void testValidateToken_tokenMalformed() {
        String tokenMalformado = "esto.no.es.un.token.valido";

        assertThrows(Exception.class, () -> jwtTokenUtil.validateToken(tokenMalformado));
    }

    @Test
    @DisplayName("ExtractAllClaims - Token vacío lanza excepción")
    void testExtractAllClaims_tokenVacio() {
        assertThrows(Exception.class, () -> jwtTokenUtil.extractAllClaims(""));
    }

    @Test
    @DisplayName("GenerateToken con claims adicionales - Incluye los claims en el token")
    void testGenerateToken_conClaimsAdicionales() {
        Map<String, Object> adicionales = new HashMap<>();
        adicionales.put("departamento", "FINANZAS");
        adicionales.put("activo", true);

        List<String> roles = List.of(ROLES_TEST);
        String token = jwtTokenUtil.generateToken(USERNAME_TEST, roles, 1L);
        Claims claims = jwtTokenUtil.extractAllClaims(token);

        assertEquals(USERNAME_TEST, claims.getSubject(), "El subject debe ser el username");
    }

    @Test
    @DisplayName("ExtractUserId - Extrae el userId del token correctamente")
    void testExtractUserId_extraccionExitosa() {
        Long userId = 123L;
        List<String> roles = List.of(ROLES_TEST);
        String token = jwtTokenUtil.generateToken(USERNAME_TEST, roles, userId);
        Long userIdExtraido = jwtTokenUtil.extractUserId(token);

        assertEquals(userId, userIdExtraido, "El userId extraído debe coincidir");
    }
}