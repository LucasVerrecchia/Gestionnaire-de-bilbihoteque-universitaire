package fr.atex.bibliotheque.loans.application.usecases;

import fr.atex.bibliotheque.loans.domain.LoanId;

public interface ReturnCopy {
    void handle(LoanId loanId);
}

