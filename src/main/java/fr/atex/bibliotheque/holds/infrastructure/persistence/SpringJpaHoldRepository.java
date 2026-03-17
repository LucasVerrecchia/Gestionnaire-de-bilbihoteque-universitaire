package fr.atex.bibliotheque.holds.infrastructure.persistence;

import fr.atex.bibliotheque.holds.domain.Hold;
import fr.atex.bibliotheque.holds.domain.HoldId;
import fr.atex.bibliotheque.holds.domain.HoldStatus;
import fr.atex.bibliotheque.users.domain.UserId;
import fr.atex.bibliotheque.works.domain.WorkId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpringJpaHoldRepository extends JpaRepository<Hold, Long> {
    Optional<Hold> findById(HoldId id);
    List<Hold> findByUserId(UserId userId);
    List<Hold> findByWorkIdAndStatus(WorkId workId, HoldStatus status);
    boolean existsByUserIdAndWorkIdAndStatusIn(UserId userId, WorkId workId, List<HoldStatus> statuses);
}

