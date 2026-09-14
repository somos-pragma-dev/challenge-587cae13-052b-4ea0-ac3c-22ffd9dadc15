package com.financiera.prestamos.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO que representa la respuesta de autenticación exitosa.
 * 
 * Este record contiene el token JWT generado tras una autenticación
 * exitosa, junto con información adicional del usuario autenticado.
 * Se retorna como cuerpo de respuesta en el endpoint de login.
 * 
 * El token JWT incluye los claims del usuario (username, roles) y
 * tiene una vigencia configurada en application.properties.
 * 
 * @param token Token JWT para solicitudes autenticadas
 * @param username Nombre de usuario autenticado
 * @param roles Roles asignados al usuario (USER, ADMIN)
 * @param type Tipo de token (Bearer)
 * @param expirationMs Tiempo de expiración en milisegundos
 */
public record AuthResponse(
    @JsonProperty("token")
    String token,
    
    @JsonProperty("username")
    String username,
    
    @JsonProperty("roles")
    String roles,
    
    @JsonProperty("type")
    String type,
    
    @JsonProperty("expiresIn")
    Long expirationMs
) {
    /**
     * Factory method para crear una respuesta de autenticación exitosa.
     * Configura automáticamente el tipo de token como "Bearer" que es
     * el estándar OAuth 2.0 para tokens JWT.
     */
    public static AuthResponse success(String token, String username, String roles, Long expirationMs) {
        return new AuthResponse(token, username, roles, "Bearer", expirationMs);
    }
}