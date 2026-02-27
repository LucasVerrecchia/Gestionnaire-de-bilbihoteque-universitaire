package fr.atex.bibliotheque.copies.domain;

import fr.atex.bibliotheque.copies.application.models.CreateCopyRequest;
import fr.atex.bibliotheque.works.domain.WorkId;
import jakarta.persistence.*;

@Entity
@Table(name = "copy")
@Access(AccessType.FIELD)
public class Copy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long technicalId;

    @Embedded
    private CopyId id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "work_id", nullable = false))
    private WorkId workId;

    @Column(nullable = false, unique = true)
    private String barcode;

    @Column(nullable = false)
    private String campus;

    @Column
    private String shelf;

    @Column(nullable = false)
    private String condition; // GOOD, WORN, DAMAGED

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CopyStatus status;

    protected Copy() {}

    private Copy(CopyId id, WorkId workId, String barcode, String campus,
                 String shelf, String condition) {
        this.id = id;
        this.workId = workId;
        this.barcode = barcode;
        this.campus = campus;
        this.shelf = shelf;
        this.condition = condition;
        this.status = CopyStatus.AVAILABLE;
    }

    public static Copy create(CopyId id, WorkId workId, CreateCopyRequest request) {
        return new Copy(id, workId, request.barcode(), request.campus(),
                request.shelf(), request.condition());
    }

    public Long getTechnicalId() { return technicalId; }
    public CopyId getId() { return id; }
    public WorkId getWorkId() { return workId; }
    public String getBarcode() { return barcode; }
    public String getCampus() { return campus; }
    public String getShelf() { return shelf; }
    public String getCondition() { return condition; }
    public CopyStatus getStatus() { return status; }

    public boolean isAvailable() {
        return status == CopyStatus.AVAILABLE;
    }

    public void markOnLoan() {
        this.status = CopyStatus.ON_LOAN;
    }

    public void markAvailable() {
        this.status = CopyStatus.AVAILABLE;
    }

    public void markReserved() {
        this.status = CopyStatus.RESERVED;
    }

    public void updateStatus(CopyStatus newStatus) {
        this.status = newStatus;
    }
}

