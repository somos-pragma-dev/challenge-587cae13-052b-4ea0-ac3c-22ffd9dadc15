package com.financiera.prestamos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Punto de entrada principal de la aplicación de Gestión de Préstamos.
 * 
 * Esta clase configura y arranca el contexto de Spring Boot, inicializando
 * todos los componentes de la aplicación incluyendo seguridad, persistencia
 * y controladores REST.
 * 
 * La aplicación implementa un sistema de préstamos con autenticación JWT
 * para proteger los endpoints sensibles y garantizar la seguridad de las
 * transacciones financieras.
 */
@SpringBootApplication
public class PrestamosApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrestamosApplication.class, args);
    }

    /**
     * Bean para el codificador de contraseñas utilizado en la autenticación.
     * BCrypt es un algoritmo de hash seguro que incluye sal para prevenir
     * ataques de tabla rainbow y fuerza bruta.
     * 
     * Este bean se inyecta en AuthService para codificar contraseñas
     * durante el registro de usuarios y comparar hashes durante el login.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}