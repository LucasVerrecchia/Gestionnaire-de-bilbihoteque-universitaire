package fr.atex.bibliotheque.loans.application.usecases;

import fr.atex.bibliotheque.loans.domain.Loan;
import fr.atex.bibliotheque.users.domain.UserId;

import java.util.List;

public interface GetLoansByUser {
    List<Loan> handle(UserId userId);
}

