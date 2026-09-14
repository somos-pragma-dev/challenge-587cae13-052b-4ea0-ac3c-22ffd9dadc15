package com.financiera.prestamos.service;

import com.financiera.prestamos.dto.LoanRequest;
import com.financiera.prestamos.model.Loan;
import com.financiera.prestamos.model.User;
import com.financiera.prestamos.repository.LoanRepository;
import com.financiera.prestamos.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final UserRepository userRepository;

    public LoanService(LoanRepository loanRepository, UserRepository userRepository) {
        this.loanRepository = loanRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Loan createLoan(LoanRequest request) {
        validateLoanRequest(request);

        Optional<User> userOpt = userRepository.findById(request.userId());
        User user = userOpt.orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con ID: " + request.userId()));

        Loan loan = new Loan();
        loan.setUser(user);
        loan.setAmount(request.amount());
        loan.setInterestRate(request.interestRate());
        loan.setTermMonths(request.termMonths());
        loan.setStatus(Loan.LoanStatus.PENDIENTE);
        loan.setRequestedDate(LocalDate.now());
        loan.setPurpose(request.purpose());

        BigDecimal monthlyPayment = calculateMonthlyPayment(
                loan.getAmount(),
                loan.getInterestRate(),
                loan.getTermMonths()
        );
        loan.setMonthlyPayment(monthlyPayment);

        return loanRepository.save(loan);
    }

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public Loan getLoanById(Long id) {
        Optional<Loan> loanOpt = loanRepository.findById(id);
        return loanOpt.orElseThrow(() -> new IllegalArgumentException("Préstamo no encontrado con ID: " + id));
    }

    @Transactional
    public Loan updateLoan(Long id, LoanRequest request) {
        Loan existingLoan = getLoanById(id);

        validateLoanRequest(request);

        existingLoan.setAmount(request.amount());
        existingLoan.setInterestRate(request.interestRate());
        existingLoan.setTermMonths(request.termMonths());
        existingLoan.setPurpose(request.purpose());

        BigDecimal monthlyPayment = calculateMonthlyPayment(
                existingLoan.getAmount(),
                existingLoan.getInterestRate(),
                existingLoan.getTermMonths()
        );
        existingLoan.setMonthlyPayment(monthlyPayment);

        return loanRepository.save(existingLoan);
    }

    @Transactional
    public void deleteLoan(Long id) {
        boolean exists = loanRepository.existsById(id);
        if (!exists) {
            throw new IllegalArgumentException("Préstamo no encontrado con ID: " + id);
        }
        loanRepository.deleteById(id);
    }

    public List<Loan> getLoansByUserId(Long userId) {
        boolean userExists = userRepository.existsById(userId);
        if (!userExists) {
            throw new IllegalArgumentException("Usuario no encontrado con ID: " + userId);
        }
        return loanRepository.findByUserId(userId);
    }

    @Transactional
    public Loan approveLoan(Long id) {
        Loan loan = getLoanById(id);
        if (loan.getStatus() != Loan.LoanStatus.PENDIENTE) {
            throw new IllegalStateException("Solo se pueden aprobar préstamos en estado PENDIENTE");
        }
        loan.setStatus(Loan.LoanStatus.APROBADO);
        loan.setApprovalDate(LocalDate.now());
        return loanRepository.save(loan);
    }

    @Transactional
    public Loan rejectLoan(Long id) {
        Loan loan = getLoanById(id);
        if (loan.getStatus() != Loan.LoanStatus.PENDIENTE) {
            throw new IllegalStateException("Solo se pueden rechazar préstamos en estado PENDIENTE");
        }
        loan.setStatus(Loan.LoanStatus.RECHAZADO);
        return loanRepository.save(loan);
    }

    private void validateLoanRequest(LoanRequest request) {
        if (request.amount() == null || request.amount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto del préstamo debe ser mayor a cero");
        }
        if (request.interestRate() == null || request.interestRate().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("La tasa de interés no puede ser negativa");
        }
        if (request.termMonths() == null || request.termMonths() <= 0) {
            throw new IllegalArgumentException("El plazo en meses debe ser mayor a cero");
        }
    }

    private BigDecimal calculateMonthlyPayment(BigDecimal amount, BigDecimal annualInterestRate, Integer termMonths) {
        if (annualInterestRate.compareTo(BigDecimal.ZERO) == 0) {
            return amount.divide(BigDecimal.valueOf(termMonths), 2, java.math.RoundingMode.HALF_UP);
        }

        BigDecimal monthlyRate = annualInterestRate.divide(BigDecimal.valueOf(12), 6, java.math.RoundingMode.HALF_UP);
        BigDecimal onePlusR = BigDecimal.ONE.add(monthlyRate);
        BigDecimal factor = onePlusR.pow(termMonths);

        BigDecimal numerator = amount.multiply(monthlyRate).multiply(factor);
        BigDecimal denominator = factor.subtract(BigDecimal.ONE);

        return numerator.divide(denominator, 2, java.math.RoundingMode.HALF_UP);
    }
}