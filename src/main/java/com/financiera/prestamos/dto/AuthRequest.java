package com.financiera.prestamos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO que representa la solicitud de autenticación de un usuario.
 * 
 * Este record contiene las credenciales necesarias para autenticar
 * a un usuario en el sistema de préstamos. Se utiliza como cuerpo
 * de la petición POST al endpoint de login.
 * 
 * La validación asegura que el username y password no estén vacíos
 * y cumplan con las longitudes mínimas requeridas.
 * 
 * @param username Nombre de usuario único en el sistema
 * @param password Contraseña del usuario (se compara con hash almacenado)
 */
public record AuthRequest(
    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Size(min = 3, max = 50, message = "El nombre de usuario debe tener entre 3 y 50 caracteres")
    String username,
    
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, max = 100, message = "La contraseña debe tener entre 6 y 100 caracteres")
    String password
) {
    /**
     * Constructor canónico que valida los parámetros antes de crear el record.
     * Aunque los validadores de Jakarta trabajan a nivel de Controller,
     * este constructor proporciona una capa adicional de validación.
     */
    public AuthRequest {
        if (username != null) {
            username = username.trim();
        }
        if (password != null) {
            password = password.trim();
        }
    }
}