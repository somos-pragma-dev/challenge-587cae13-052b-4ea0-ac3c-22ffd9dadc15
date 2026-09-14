package com.financiera.prestamos.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * DTO que representa la solicitud de un préstamo financiero.
 * 
 * Este record contiene todos los datos necesarios para procesar una
 * solicitud de préstamo, incluyendo información del cliente, monto
 * solicitado y propósito del préstamo. Se utiliza como cuerpo de
 * petición POST al endpoint de creación de préstamos.
 * 
 * Los campos son validados para asegurar que los datos sean válidos
 * antes de ser procesados por el servicio de préstamos.
 * 
 * @param clientName Nombre completo del solicitante
 * @param clientEmail Correo electrónico del solicitante
 * @param clientId Número de identificación del cliente
 * @param amount Monto solicitado del préstamo
 * @param termMonths Plazo del préstamo en meses
 * @param purpose Propósito o destino del préstamo
 * @param interestRate Tasa de interés anual aplicada
 */
public record LoanRequest(
    @NotBlank(message = "El nombre del cliente es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    @JsonProperty("clientName")
    String clientName,
    
    @NotBlank(message = "El correo electrónico es obligatorio")
    @Size(max = 100, message = "El correo no puede exceder 100 caracteres")
    @JsonProperty("clientEmail")
    String clientEmail,
    
    @NotBlank(message = "La identificación del cliente es obligatoria")
    @Size(min = 5, max = 20, message = "La identificación debe tener entre 5 y 20 caracteres")
    @JsonProperty("clientId")
    String clientId,
    
    @NotNull(message = "El monto del préstamo es obligatorio")
    @DecimalMin(value = "100.00", message = "El monto mínimo es 100")
    @JsonProperty("amount")
    BigDecimal amount,
    
    @NotNull(message = "El plazo del préstamo es obligatorio")
    @DecimalMin(value = "1", message = "El plazo mínimo es 1 mes")
    @JsonProperty("termMonths")
    Integer termMonths,
    
    @NotBlank(message = "El propósito del préstamo es obligatorio")
    @Size(min = 5, max = 200, message = "El propósito debe tener entre 5 y 200 caracteres")
    @JsonProperty("purpose")
    String purpose,
    
    @JsonProperty("interestRate")
    BigDecimal interestRate
) {
    /**
     * Constructor que normaliza los datos del solicitud.
     * Elimina espacios en blanco y aplica valores por defecto
     * cuando ciertos campos opcionales no se proporcionan.
     */
    public LoanRequest {
        if (clientName != null) {
            clientName = clientName.trim();
        }
        if (clientEmail != null) {
            clientEmail = clientEmail.trim().toLowerCase();
        }
        if (clientId != null) {
            clientId = clientId.trim();
        }
        if (purpose != null) {
            purpose = purpose.trim();
        }
        if (interestRate == null) {
            interestRate = new BigDecimal("0.15");
        }
    }
}