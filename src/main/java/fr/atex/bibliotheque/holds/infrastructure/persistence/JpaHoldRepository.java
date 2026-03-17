package fr.atex.bibliotheque.holds.infrastructure.persistence;

import fr.atex.bibliotheque.holds.application.gateways.HoldRepository;
import fr.atex.bibliotheque.holds.domain.Hold;
import fr.atex.bibliotheque.holds.domain.HoldId;
import fr.atex.bibliotheque.holds.domain.HoldStatus;
import fr.atex.bibliotheque.users.domain.UserId;
import fr.atex.bibliotheque.works.domain.WorkId;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
class JpaHoldRepository implements HoldRepository {

    private final SpringJpaHoldRepository jpaRepository;

    JpaHoldRepository(SpringJpaHoldRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void save(Hold hold) { jpaRepository.save(hold); }

    @Override
    public Optional<Hold> findById(HoldId id) { return jpaRepository.findById(id); }

    @Override
    public List<Hold> findByUserId(UserId userId) { return jpaRepository.findByUserId(userId); }

    @Override
    public List<Hold> findByWorkIdAndStatus(WorkId workId, HoldStatus status) {
        return jpaRepository.findByWorkIdAndStatus(workId, status);
    }

    @Override
    public boolean existsByUserIdAndWorkIdAndStatusIn(UserId userId, WorkId workId, List<HoldStatus> statuses) {
        return jpaRepository.existsByUserIdAndWorkIdAndStatusIn(userId, workId, statuses);
    }
}

