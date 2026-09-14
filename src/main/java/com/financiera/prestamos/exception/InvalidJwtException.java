package com.financiera.prestamos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class InvalidJwtException extends RuntimeException {
    private final String token;
    private final String reason;

    public InvalidJwtException(String message) {
        super(message);
        this.token = null;
        this.reason = message;
    }

    public InvalidJwtException(String message, String token) {
        super(message);
        this.token = token;
        this.reason = message;
    }

    public InvalidJwtException(String message, Throwable cause) {
        super(message, cause);
        this.token = null;
        this.reason = message;
    }

    public InvalidJwtException(String message, String token, Throwable cause) {
        super(message, cause);
        this.token = token;
        this.reason = message;
    }

    public String getToken() {
        return token;
    }

    public String getReason() {
        return reason;
    }

    public boolean isTokenPresent() {
        return token != null && !token.isBlank();
    }

    public String getMaskedToken() {
        if (token == null || token.length() < 20) {
            return "[TOKEN_NOT_AVAILABLE]";
        }
        return token.substring(0, 10) + "..." + token.substring(token.length() - 10);
    }

    @Override
    public String toString() {
        return String.format("InvalidJwtException{message='%s', reason='%s', tokenPresent=%s}", 
            getMessage(), reason, isTokenPresent());
    }
}