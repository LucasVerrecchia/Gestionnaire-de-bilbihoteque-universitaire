package fr.atex.bibliotheque.loans.application.services;

import fr.atex.bibliotheque.loans.application.gateways.LoanRepository;
import fr.atex.bibliotheque.loans.application.usecases.GetLoansByUser;
import fr.atex.bibliotheque.loans.domain.Loan;
import fr.atex.bibliotheque.users.domain.UserId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
class GetLoansByUserHandler implements GetLoansByUser {

    private final LoanRepository loanRepository;

    GetLoansByUserHandler(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Loan> handle(UserId userId) {
        return loanRepository.findByUserId(userId);
    }
}

