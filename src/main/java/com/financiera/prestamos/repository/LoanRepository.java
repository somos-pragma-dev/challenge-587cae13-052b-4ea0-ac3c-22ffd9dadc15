package com.financiera.prestamos.repository;

import com.financiera.prestamos.model.Loan;
import com.financiera.prestamos.model.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findByUserId(Long userId);

    List<Loan> findByUserIdAndStatus(Long userId, LoanStatus status);

    List<Loan> findByStatus(LoanStatus status);

    Optional<Loan> findByIdAndUserId(Long loanId, Long userId);

    @Query("SELECT l FROM Loan l WHERE l.user.id = :userId ORDER BY l.createdAt DESC")
    List<Loan> findRecentLoansByUserId(@Param("userId") Long userId);

    @Query("SELECT l FROM Loan l WHERE l.status = :status AND l.createdAt >= :startDate")
    List<Loan> findLoansByStatusAndDateRange(@Param("status") LoanStatus status,
                                              @Param("startDate") LocalDate startDate);

    @Query("SELECT SUM(l.amount) FROM Loan l WHERE l.user.id = :userId AND l.status = :status")
    BigDecimal sumAmountByUserIdAndStatus(@Param("userId") Long userId, @Param("status") LoanStatus status);

    @Query("SELECT COUNT(l) FROM Loan l WHERE l.user.id = :userId")
    Long countByUserId(@Param("userId") Long userId);

    @Query("SELECT COUNT(l) FROM Loan l WHERE l.status = :status")
    Long countByStatus(@Param("status") LoanStatus status);

    @Query("SELECT l FROM Loan l WHERE l.user.id = :userId AND l.status IN :statuses")
    List<Loan> findByUserIdAndStatusIn(@Param("userId") Long userId, @Param("statuses") List<LoanStatus> statuses);

    @Query("SELECT l FROM Loan l WHERE l.amount >= :minAmount AND l.amount <= :maxAmount")
    List<Loan> findByAmountRange(@Param("minAmount") BigDecimal minAmount, @Param("maxAmount") BigDecimal maxAmount);
}