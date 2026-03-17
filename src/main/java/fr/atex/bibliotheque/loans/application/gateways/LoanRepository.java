package fr.atex.bibliotheque.loans.application.gateways;

import fr.atex.bibliotheque.copies.domain.CopyId;
import fr.atex.bibliotheque.loans.domain.Loan;
import fr.atex.bibliotheque.loans.domain.LoanId;
import fr.atex.bibliotheque.loans.domain.LoanStatus;
import fr.atex.bibliotheque.users.domain.UserId;

import java.util.List;
import java.util.Optional;

public interface LoanRepository {
    void save(Loan loan);
    Optional<Loan> findById(LoanId id);
    List<Loan> findByUserId(UserId userId);
    List<Loan> findByUserIdAndStatus(UserId userId, LoanStatus status);
    Optional<Loan> findActiveLoanByCopyId(CopyId copyId);
    long countActiveByUserId(UserId userId);
}

