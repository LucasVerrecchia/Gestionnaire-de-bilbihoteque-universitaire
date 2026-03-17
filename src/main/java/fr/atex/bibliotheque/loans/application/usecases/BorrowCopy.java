package fr.atex.bibliotheque.loans.application.usecases;

import fr.atex.bibliotheque.loans.application.models.BorrowCopyRequest;
import fr.atex.bibliotheque.loans.domain.LoanId;

public interface BorrowCopy {
    LoanId handle(BorrowCopyRequest request);
}

