package fr.atex.bibliotheque.holds.application.usecases;

import fr.atex.bibliotheque.holds.domain.Hold;
import fr.atex.bibliotheque.users.domain.UserId;

import java.util.List;

public interface GetHoldsByUser {
    List<Hold> handle(UserId userId);
}

