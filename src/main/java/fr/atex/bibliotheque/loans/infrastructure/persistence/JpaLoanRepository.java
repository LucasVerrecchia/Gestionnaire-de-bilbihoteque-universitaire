package fr.atex.bibliotheque.loans.infrastructure.persistence;

import fr.atex.bibliotheque.copies.domain.CopyId;
import fr.atex.bibliotheque.loans.application.gateways.LoanRepository;
import fr.atex.bibliotheque.loans.domain.Loan;
import fr.atex.bibliotheque.loans.domain.LoanId;
import fr.atex.bibliotheque.loans.domain.LoanStatus;
import fr.atex.bibliotheque.users.domain.UserId;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
class JpaLoanRepository implements LoanRepository {

    private final SpringJpaLoanRepository jpaRepository;

    JpaLoanRepository(SpringJpaLoanRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void save(Loan loan) {
        jpaRepository.save(loan);
    }

    @Override
    public Optional<Loan> findById(LoanId id) {
        return jpaRepository.findById(id);
    }

    @Override
    public List<Loan> findByUserId(UserId userId) {
        return jpaRepository.findByUserId(userId);
    }

    @Override
    public List<Loan> findByUserIdAndStatus(UserId userId, LoanStatus status) {
        return jpaRepository.findByUserIdAndStatus(userId, status);
    }

    @Override
    public Optional<Loan> findActiveLoanByCopyId(CopyId copyId) {
        return jpaRepository.findActiveByCopyId(copyId);
    }

    @Override
    public long countActiveByUserId(UserId userId) {
        return jpaRepository.countActiveByUserId(userId);
    }
}

