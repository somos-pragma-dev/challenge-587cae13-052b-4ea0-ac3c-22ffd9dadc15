package com.financiera.prestamos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyExistsException extends RuntimeException {
    private final String username;
    private final String email;
    private final Long existingUserId;

    public UserAlreadyExistsException(String message) {
        super(message);
        this.username = null;
        this.email = null;
        this.existingUserId = null;
    }

    public UserAlreadyExistsException(String message, String username) {
        super(message);
        this.username = username;
        this.email = null;
        this.existingUserId = null;
    }

    public UserAlreadyExistsException(String message, String username, String email) {
        super(message);
        this.username = username;
        this.email = email;
        this.existingUserId = null;
    }

    public UserAlreadyExistsException(String message, String username, String email, Long existingUserId) {
        super(message);
        this.username = username;
        this.email = email;
        this.existingUserId = existingUserId;
    }

    public UserAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
        this.username = null;
        this.email = null;
        this.existingUserId = null;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Long getExistingUserId() {
        return existingUserId;
    }

    public boolean hasUsername() {
        return username != null && !username.isBlank();
    }

    public boolean hasEmail() {
        return email != null && !email.isBlank();
    }

    public boolean hasExistingUserId() {
        return existingUserId != null;
    }

    public String getConflictType() {
        if (hasUsername() && hasEmail()) {
            return "USERNAME_AND_EMAIL";
        } else if (hasUsername()) {
            return "USERNAME";
        } else if (hasEmail()) {
            return "EMAIL";
        }
        return "UNKNOWN";
    }

    @Override
    public String toString() {
        return String.format("UserAlreadyExistsException{message='%s', username='%s', email='%s', existingUserId=%s, conflictType='%s'}", 
            getMessage(), username, email, existingUserId, getConflictType());
    }
}