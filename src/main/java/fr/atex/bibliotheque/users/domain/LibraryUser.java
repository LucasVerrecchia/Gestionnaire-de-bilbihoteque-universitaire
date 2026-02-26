package fr.atex.bibliotheque.users.domain;

import fr.atex.bibliotheque.users.application.models.CreateUserRequest;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "library_user")
@Access(AccessType.FIELD)
public class LibraryUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long technicalId;

    @Embedded
    private UserId id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserCategory category;

    @Column(nullable = false)
    private boolean blocked;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    protected LibraryUser() {}

    private LibraryUser(UserId id, String firstName, String lastName, String email,
                        UserCategory category, Instant createdAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.category = category;
        this.blocked = false;
        this.createdAt = createdAt;
    }

    public static LibraryUser create(UserId id, CreateUserRequest request, Instant createdAt) {
        return new LibraryUser(id, request.firstName(), request.lastName(),
                request.email(), request.category(), createdAt);
    }

    public Long getTechnicalId() { return technicalId; }
    public UserId getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public UserCategory getCategory() { return category; }
    public boolean isBlocked() { return blocked; }
    public Instant getCreatedAt() { return createdAt; }

    public int getMaxLoans() {
        return switch (category) {
            case STUDENT -> 5;
            case TEACHER, RESEARCHER -> 20;
        };
    }

    public int getLoanDurationDays() {
        return switch (category) {
            case STUDENT -> 21;
            case TEACHER, RESEARCHER -> 60;
        };
    }

    public int getMaxRenewals() {
        return switch (category) {
            case STUDENT -> 2;
            case TEACHER, RESEARCHER -> 3;
        };
    }

    public void block() { this.blocked = true; }
    public void unblock() { this.blocked = false; }
}

