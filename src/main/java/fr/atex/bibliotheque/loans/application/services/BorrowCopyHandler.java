package fr.atex.bibliotheque.loans.application.services;

import fr.atex.bibliotheque.copies.application.gateways.CopyRepository;
import fr.atex.bibliotheque.copies.domain.CopyId;
import fr.atex.bibliotheque.loans.application.gateways.LoanRepository;
import fr.atex.bibliotheque.loans.application.models.BorrowCopyRequest;
import fr.atex.bibliotheque.loans.application.usecases.BorrowCopy;
import fr.atex.bibliotheque.loans.domain.Loan;
import fr.atex.bibliotheque.loans.domain.LoanId;
import fr.atex.bibliotheque.shared.DomainIdGenerator;
import fr.atex.bibliotheque.shared.TimeProvider;
import fr.atex.bibliotheque.shared.error.BusinessException;
import fr.atex.bibliotheque.users.application.gateways.UserRepository;
import fr.atex.bibliotheque.users.domain.UserId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class BorrowCopyHandler implements BorrowCopy {

    private static final Logger logger = LoggerFactory.getLogger(BorrowCopyHandler.class);

    private final DomainIdGenerator idGenerator;
    private final TimeProvider timeProvider;
    private final LoanRepository loanRepository;
    private final CopyRepository copyRepository;
    private final UserRepository userRepository;

    BorrowCopyHandler(DomainIdGenerator idGenerator, TimeProvider timeProvider,
                      LoanRepository loanRepository, CopyRepository copyRepository,
                      UserRepository userRepository) {
        this.idGenerator = idGenerator;
        this.timeProvider = timeProvider;
        this.loanRepository = loanRepository;
        this.copyRepository = copyRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public LoanId handle(BorrowCopyRequest request) {
        final var copyId = new CopyId(request.copyId());
        final var userId = new UserId(request.userId());

        logger.info("Emprunt : userId={}, copyId={}", userId.value(), copyId.value());

        final var user = userRepository.findById(userId)
                .orElseThrow(() -> new java.util.NoSuchElementException("Lecteur introuvable : " + userId.value()));

        if (user.isBlocked()) {
            throw new BusinessException("Le lecteur " + userId.value() + " est bloqué");
        }

        final var copy = copyRepository.findById(copyId)
                .orElseThrow(() -> new java.util.NoSuchElementException("Exemplaire introuvable : " + copyId.value()));

        if (!copy.isAvailable()) {
            throw new BusinessException("L'exemplaire " + copyId.value() + " n'est pas disponible");
        }

        final long activeLoans = loanRepository.countActiveByUserId(userId);
        if (activeLoans >= user.getMaxLoans()) {
            throw new BusinessException("Le lecteur a atteint sa limite de " + user.getMaxLoans() + " emprunts simultanés");
        }

        final var loanId = new LoanId(idGenerator.generate());
        final var loan = Loan.create(loanId, copyId, userId, timeProvider.now(), user.getLoanDurationDays());

        copy.markOnLoan();
        copyRepository.save(copy);
        loanRepository.save(loan);

        logger.debug("Emprunt créé : id={}", loanId.value());
        return loanId;
    }
}

