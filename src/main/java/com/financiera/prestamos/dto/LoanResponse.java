package com.financiera.prestamos.dto;

import com.financiera.prestamos.model.Loan;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record LoanResponse(
    Long id,
    String username,
    BigDecimal amount,
    BigDecimal interestRate,
    Integer termMonths,
    String status,
    LocalDate requestedDate,
    LocalDate approvalDate,
    LocalDate disbursementDate,
    LocalDate dueDate,
    BigDecimal totalPayment,
    BigDecimal monthlyPayment
) {
    public static LoanResponse fromEntity(Loan loan) {
        return new LoanResponse(
            loan.getId(),
            loan.getUser() != null ? loan.getUser().getUsername() : null,
            loan.getAmount(),
            loan.getInterestRate(),
            loan.getTermMonths(),
            loan.getStatus().name(),
            loan.getRequestedDate(),
            loan.getApprovalDate(),
            loan.getDisbursementDate(),
            loan.getDueDate(),
            loan.getTotalPayment(),
            loan.getMonthlyPayment()
        );
    }

    public static LoanResponse pending(Long id, String username, BigDecimal amount, Integer termMonths) {
        return new LoanResponse(
            id,
            username,
            amount,
            BigDecimal.ZERO,
            termMonths,
            "PENDING",
            LocalDate.now(),
            null,
            null,
            null,
            null,
            null
        );
    }
}