package fr.atex.bibliotheque.holds.application.services;

import fr.atex.bibliotheque.holds.application.gateways.HoldRepository;
import fr.atex.bibliotheque.holds.application.models.CreateHoldRequest;
import fr.atex.bibliotheque.holds.application.usecases.CreateHold;
import fr.atex.bibliotheque.holds.domain.Hold;
import fr.atex.bibliotheque.holds.domain.HoldId;
import fr.atex.bibliotheque.holds.domain.HoldStatus;
import fr.atex.bibliotheque.shared.DomainIdGenerator;
import fr.atex.bibliotheque.shared.TimeProvider;
import fr.atex.bibliotheque.shared.error.BusinessException;
import fr.atex.bibliotheque.users.application.gateways.UserRepository;
import fr.atex.bibliotheque.users.domain.UserId;
import fr.atex.bibliotheque.works.application.gateways.WorkRepository;
import fr.atex.bibliotheque.works.domain.WorkId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
class CreateHoldHandler implements CreateHold {

    private static final Logger logger = LoggerFactory.getLogger(CreateHoldHandler.class);

    private final DomainIdGenerator idGenerator;
    private final TimeProvider timeProvider;
    private final HoldRepository holdRepository;
    private final WorkRepository workRepository;
    private final UserRepository userRepository;

    CreateHoldHandler(DomainIdGenerator idGenerator, TimeProvider timeProvider,
                      HoldRepository holdRepository, WorkRepository workRepository,
                      UserRepository userRepository) {
        this.idGenerator = idGenerator;
        this.timeProvider = timeProvider;
        this.holdRepository = holdRepository;
        this.workRepository = workRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public HoldId handle(CreateHoldRequest request) {
        final var workId = new WorkId(request.workId());
        final var userId = new UserId(request.userId());

        logger.info("Réservation : userId={}, workId={}", userId.value(), workId.value());

        workRepository.findById(workId)
                .orElseThrow(() -> new java.util.NoSuchElementException("Œuvre introuvable : " + workId.value()));

        userRepository.findById(userId)
                .orElseThrow(() -> new java.util.NoSuchElementException("Lecteur introuvable : " + userId.value()));

        final boolean alreadyHasPendingOrReady = holdRepository.existsByUserIdAndWorkIdAndStatusIn(
                userId, workId, List.of(HoldStatus.PENDING, HoldStatus.READY));
        if (alreadyHasPendingOrReady) {
            throw new BusinessException("Le lecteur a déjà une réservation active pour cette œuvre");
        }

        final var holdId = new HoldId(idGenerator.generate());
        final var hold = Hold.create(holdId, workId, userId, timeProvider.now());
        holdRepository.save(hold);

        logger.debug("Réservation créée : id={}", holdId.value());
        return holdId;
    }
}

