package fr.atex.bibliotheque.holds.application.services;

import fr.atex.bibliotheque.holds.application.gateways.HoldRepository;
import fr.atex.bibliotheque.holds.application.usecases.GetHoldsByUser;
import fr.atex.bibliotheque.holds.domain.Hold;
import fr.atex.bibliotheque.users.domain.UserId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
class GetHoldsByUserHandler implements GetHoldsByUser {

    private final HoldRepository holdRepository;

    GetHoldsByUserHandler(HoldRepository holdRepository) {
        this.holdRepository = holdRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Hold> handle(UserId userId) {
        return holdRepository.findByUserId(userId);
    }
}

