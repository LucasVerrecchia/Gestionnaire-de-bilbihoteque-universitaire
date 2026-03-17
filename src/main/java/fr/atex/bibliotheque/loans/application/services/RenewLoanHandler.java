package fr.atex.bibliotheque.loans.application.services;

import fr.atex.bibliotheque.loans.application.gateways.LoanRepository;
import fr.atex.bibliotheque.loans.application.usecases.RenewLoan;
import fr.atex.bibliotheque.loans.domain.LoanId;
import fr.atex.bibliotheque.shared.error.BusinessException;
import fr.atex.bibliotheque.users.application.gateways.UserRepository;
import fr.atex.bibliotheque.users.domain.UserId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class RenewLoanHandler implements RenewLoan {

    private static final Logger logger = LoggerFactory.getLogger(RenewLoanHandler.class);

    private final LoanRepository loanRepository;
    private final UserRepository userRepository;

    RenewLoanHandler(LoanRepository loanRepository, UserRepository userRepository) {
        this.loanRepository = loanRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public void handle(LoanId loanId) {
        logger.info("Prolongation de l'emprunt : loanId={}", loanId.value());

        final var loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new java.util.NoSuchElementException("Emprunt introuvable : " + loanId.value()));

        if (!loan.isActive()) {
            throw new BusinessException("L'emprunt " + loanId.value() + " n'est pas actif");
        }

        final var user = userRepository.findById(loan.getUserId())
                .orElseThrow(() -> new java.util.NoSuchElementException("Lecteur introuvable : " + loan.getUserId().value()));

        if (loan.getRenewCount() >= user.getMaxRenewals()) {
            throw new BusinessException("Nombre maximum de prolongations atteint (" + user.getMaxRenewals() + ")");
        }

        loan.renew(user.getLoanDurationDays());
        loanRepository.save(loan);

        logger.debug("Emprunt prolongé : loanId={}, renewCount={}", loanId.value(), loan.getRenewCount());
    }
}

