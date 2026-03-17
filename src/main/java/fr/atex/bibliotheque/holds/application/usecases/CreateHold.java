package fr.atex.bibliotheque.holds.application.usecases;

import fr.atex.bibliotheque.holds.application.models.CreateHoldRequest;
import fr.atex.bibliotheque.holds.domain.HoldId;

public interface CreateHold {
    HoldId handle(CreateHoldRequest request);
}

