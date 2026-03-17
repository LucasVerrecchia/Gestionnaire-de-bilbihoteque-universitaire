package fr.atex.bibliotheque.loans.application.services;

import fr.atex.bibliotheque.copies.application.gateways.CopyRepository;
import fr.atex.bibliotheque.loans.application.gateways.LoanRepository;
import fr.atex.bibliotheque.loans.application.usecases.ReturnCopy;
import fr.atex.bibliotheque.loans.domain.LoanId;
import fr.atex.bibliotheque.shared.TimeProvider;
import fr.atex.bibliotheque.shared.error.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class ReturnCopyHandler implements ReturnCopy {

    private static final Logger logger = LoggerFactory.getLogger(ReturnCopyHandler.class);

    private final TimeProvider timeProvider;
    private final LoanRepository loanRepository;
    private final CopyRepository copyRepository;

    ReturnCopyHandler(TimeProvider timeProvider, LoanRepository loanRepository,
                      CopyRepository copyRepository) {
        this.timeProvider = timeProvider;
        this.loanRepository = loanRepository;
        this.copyRepository = copyRepository;
    }

    @Transactional
    @Override
    public void handle(LoanId loanId) {
        logger.info("Retour de l'emprunt : loanId={}", loanId.value());

        final var loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new java.util.NoSuchElementException("Emprunt introuvable : " + loanId.value()));

        if (!loan.isActive()) {
            throw new BusinessException("L'emprunt " + loanId.value() + " n'est pas actif");
        }

        final var copy = copyRepository.findById(loan.getCopyId())
                .orElseThrow(() -> new java.util.NoSuchElementException("Exemplaire introuvable : " + loan.getCopyId().value()));

        loan.returnLoan(timeProvider.now());
        copy.markAvailable();

        loanRepository.save(loan);
        copyRepository.save(copy);

        logger.debug("Retour enregistré : loanId={}", loanId.value());
    }
}

