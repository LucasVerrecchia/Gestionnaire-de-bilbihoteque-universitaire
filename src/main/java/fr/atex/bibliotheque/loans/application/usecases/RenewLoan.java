package fr.atex.bibliotheque.loans.application.usecases;

import fr.atex.bibliotheque.loans.domain.LoanId;

public interface RenewLoan {
    void handle(LoanId loanId);
}

