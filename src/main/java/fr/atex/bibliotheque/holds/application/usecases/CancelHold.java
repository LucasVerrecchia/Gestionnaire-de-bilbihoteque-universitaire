package fr.atex.bibliotheque.holds.application.usecases;

import fr.atex.bibliotheque.holds.domain.HoldId;

public interface CancelHold {
    void handle(HoldId holdId);
}

