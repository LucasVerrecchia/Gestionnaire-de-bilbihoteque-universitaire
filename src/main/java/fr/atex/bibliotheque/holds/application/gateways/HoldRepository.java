package fr.atex.bibliotheque.holds.application.gateways;

import fr.atex.bibliotheque.holds.domain.Hold;
import fr.atex.bibliotheque.holds.domain.HoldId;
import fr.atex.bibliotheque.holds.domain.HoldStatus;
import fr.atex.bibliotheque.users.domain.UserId;
import fr.atex.bibliotheque.works.domain.WorkId;

import java.util.List;
import java.util.Optional;

public interface HoldRepository {
    void save(Hold hold);
    Optional<Hold> findById(HoldId id);
    List<Hold> findByUserId(UserId userId);
    List<Hold> findByWorkIdAndStatus(WorkId workId, HoldStatus status);
    boolean existsByUserIdAndWorkIdAndStatusIn(UserId userId, WorkId workId, List<HoldStatus> statuses);
}

