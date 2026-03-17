package fr.atex.bibliotheque.holds.application.services;

import fr.atex.bibliotheque.holds.application.gateways.HoldRepository;
import fr.atex.bibliotheque.holds.application.usecases.CancelHold;
import fr.atex.bibliotheque.holds.domain.HoldId;
import fr.atex.bibliotheque.shared.error.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class CancelHoldHandler implements CancelHold {

    private static final Logger logger = LoggerFactory.getLogger(CancelHoldHandler.class);

    private final HoldRepository holdRepository;

    CancelHoldHandler(HoldRepository holdRepository) {
        this.holdRepository = holdRepository;
    }

    @Transactional
    @Override
    public void handle(HoldId holdId) {
        logger.info("Annulation de la réservation : holdId={}", holdId.value());

        final var hold = holdRepository.findById(holdId)
                .orElseThrow(() -> new java.util.NoSuchElementException("Réservation introuvable : " + holdId.value()));

        if (!hold.isCancellable()) {
            throw new BusinessException("La réservation " + holdId.value() + " ne peut pas être annulée (statut : " + hold.getStatus() + ")");
        }

        hold.cancel();
        holdRepository.save(hold);

        logger.debug("Réservation annulée : holdId={}", holdId.value());
    }
}

