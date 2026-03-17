package fr.atex.bibliotheque.loans.infrastructure.persistence;

import fr.atex.bibliotheque.copies.domain.CopyId;
import fr.atex.bibliotheque.loans.domain.Loan;
import fr.atex.bibliotheque.loans.domain.LoanId;
import fr.atex.bibliotheque.loans.domain.LoanStatus;
import fr.atex.bibliotheque.users.domain.UserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpringJpaLoanRepository extends JpaRepository<Loan, Long> {
    Optional<Loan> findById(LoanId id);
    List<Loan> findByUserId(UserId userId);
    List<Loan> findByUserIdAndStatus(UserId userId, LoanStatus status);

    @Query("SELECT l FROM Loan l WHERE l.copyId = :copyId AND l.status IN ('ACTIVE', 'OVERDUE')")
    Optional<Loan> findActiveByCopyId(@Param("copyId") CopyId copyId);

    @Query("SELECT COUNT(l) FROM Loan l WHERE l.userId = :userId AND l.status IN ('ACTIVE', 'OVERDUE')")
    long countActiveByUserId(@Param("userId") UserId userId);
}

