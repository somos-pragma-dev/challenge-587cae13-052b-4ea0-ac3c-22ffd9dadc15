package com.financiera.prestamos.controller;

import com.financiera.prestamos.dto.LoanRequest;
import com.financiera.prestamos.dto.LoanResponse;
import com.financiera.prestamos.model.Loan;
import com.financiera.prestamos.service.LoanService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<LoanResponse> createLoan(@Valid @RequestBody LoanRequest request) {
        Loan createdLoan = loanService.createLoan(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(LoanResponse.fromEntity(createdLoan));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<LoanResponse>> getAllLoans() {
        List<Loan> loans = loanService.getAllLoans();
        List<LoanResponse> responses = loans.stream()
                .map(LoanResponse::fromEntity)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<LoanResponse> getLoanById(@PathVariable Long id) {
        Loan loan = loanService.getLoanById(id);
        return ResponseEntity.ok(LoanResponse.fromEntity(loan));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<LoanResponse> updateLoan(@PathVariable Long id, @Valid @RequestBody LoanRequest request) {
        Loan updatedLoan = loanService.updateLoan(id, request);
        return ResponseEntity.ok(LoanResponse.fromEntity(updatedLoan));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteLoan(@PathVariable Long id) {
        loanService.deleteLoan(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<LoanResponse>> getLoansByUserId(@PathVariable Long userId) {
        List<Loan> loans = loanService.getLoansByUserId(userId);
        List<LoanResponse> responses = loans.stream()
                .map(LoanResponse::fromEntity)
                .toList();
        return ResponseEntity.ok(responses);
    }
}