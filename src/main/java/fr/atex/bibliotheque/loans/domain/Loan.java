package fr.atex.bibliotheque.loans.domain;

import fr.atex.bibliotheque.copies.domain.CopyId;
import fr.atex.bibliotheque.users.domain.UserId;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "loan")
@Access(AccessType.FIELD)
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long technicalId;

    @Embedded
    private LoanId id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "copy_id", nullable = false))
    private CopyId copyId;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "user_id", nullable = false))
    private UserId userId;

    @Column(nullable = false, updatable = false)
    private Instant startAt;

    @Column(nullable = false)
    private Instant dueAt;

    @Column
    private Instant returnedAt;

    @Column(nullable = false)
    private int renewCount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoanStatus status;

    protected Loan() {}

    private Loan(LoanId id, CopyId copyId, UserId userId, Instant startAt, Instant dueAt) {
        this.id = id;
        this.copyId = copyId;
        this.userId = userId;
        this.startAt = startAt;
        this.dueAt = dueAt;
        this.renewCount = 0;
        this.status = LoanStatus.ACTIVE;
    }

    public static Loan create(LoanId id, CopyId copyId, UserId userId,
                              Instant startAt, int loanDurationDays) {
        Instant dueAt = startAt.plusSeconds((long) loanDurationDays * 24 * 3600);
        return new Loan(id, copyId, userId, startAt, dueAt);
    }

    public Long getTechnicalId() { return technicalId; }
    public LoanId getId() { return id; }
    public CopyId getCopyId() { return copyId; }
    public UserId getUserId() { return userId; }
    public Instant getStartAt() { return startAt; }
    public Instant getDueAt() { return dueAt; }
    public Instant getReturnedAt() { return returnedAt; }
    public int getRenewCount() { return renewCount; }
    public LoanStatus getStatus() { return status; }

    public boolean isActive() {
        return status == LoanStatus.ACTIVE || status == LoanStatus.OVERDUE;
    }

    public void returnLoan(Instant returnedAt) {
        this.returnedAt = returnedAt;
        this.status = LoanStatus.RETURNED;
    }

    public void renew(int additionalDays) {
        this.dueAt = this.dueAt.plusSeconds((long) additionalDays * 24 * 3600);
        this.renewCount++;
    }

    public void markOverdue() {
        this.status = LoanStatus.OVERDUE;
    }

    public void markLost() {
        this.status = LoanStatus.LOST_DECLARED;
    }
}

