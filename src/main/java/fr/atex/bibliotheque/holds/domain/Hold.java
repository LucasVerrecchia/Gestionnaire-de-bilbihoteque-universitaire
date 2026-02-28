package fr.atex.bibliotheque.holds.domain;

import fr.atex.bibliotheque.users.domain.UserId;
import fr.atex.bibliotheque.works.domain.WorkId;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "hold")
@Access(AccessType.FIELD)
public class Hold {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long technicalId;

    @Embedded
    private HoldId id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "work_id", nullable = false))
    private WorkId workId;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "user_id", nullable = false))
    private UserId userId;

    @Column(nullable = false, updatable = false)
    private Instant requestedAt;

    @Column
    private Instant readyAt;

    @Column
    private Instant expiresAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private HoldStatus status;

    protected Hold() {}

    private Hold(HoldId id, WorkId workId, UserId userId, Instant requestedAt) {
        this.id = id;
        this.workId = workId;
        this.userId = userId;
        this.requestedAt = requestedAt;
        this.status = HoldStatus.PENDING;
    }

    public static Hold create(HoldId id, WorkId workId, UserId userId, Instant requestedAt) {
        return new Hold(id, workId, userId, requestedAt);
    }

    public Long getTechnicalId() { return technicalId; }
    public HoldId getId() { return id; }
    public WorkId getWorkId() { return workId; }
    public UserId getUserId() { return userId; }
    public Instant getRequestedAt() { return requestedAt; }
    public Instant getReadyAt() { return readyAt; }
    public Instant getExpiresAt() { return expiresAt; }
    public HoldStatus getStatus() { return status; }

    public boolean isPending() { return status == HoldStatus.PENDING; }
    public boolean isReady() { return status == HoldStatus.READY; }
    public boolean isCancellable() {
        return status == HoldStatus.PENDING || status == HoldStatus.READY;
    }

    public void markReady(Instant readyAt, int pickupDays) {
        this.readyAt = readyAt;
        this.expiresAt = readyAt.plusSeconds((long) pickupDays * 24 * 3600);
        this.status = HoldStatus.READY;
    }

    public void cancel() {
        this.status = HoldStatus.CANCELLED;
    }

    public void fulfill() {
        this.status = HoldStatus.FULFILLED;
    }

    public void expire() {
        this.status = HoldStatus.EXPIRED;
    }
}

