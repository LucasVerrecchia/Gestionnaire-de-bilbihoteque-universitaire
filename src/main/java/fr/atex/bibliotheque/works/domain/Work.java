package fr.atex.bibliotheque.works.domain;

import fr.atex.bibliotheque.works.application.models.CreateWorkRequest;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "work")
@Access(AccessType.FIELD)
public class Work {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long technicalId;

    @Embedded
    private WorkId id;

    @Column(nullable = false)
    private String isbn;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String publisher;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private String type; // BOOK, THESIS, JOURNAL, DVD

    @Column(nullable = false)
    private String language;

    @Column(length = 2000)
    private String description;

    // Stored as comma-separated values
    @Column(nullable = false)
    private String authors;

    @Column
    private String subjects;

    protected Work() {}

    private Work(WorkId id, String isbn, String title, String publisher, Integer year,
                 String type, String language, String description, String authors, String subjects) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.publisher = publisher;
        this.year = year;
        this.type = type;
        this.language = language;
        this.description = description;
        this.authors = authors;
        this.subjects = subjects;
    }

    public static Work create(WorkId id, CreateWorkRequest request) {
        return new Work(
                id,
                request.isbn(),
                request.title(),
                request.publisher(),
                request.year(),
                request.type(),
                request.language(),
                request.description(),
                String.join(",", request.authors()),
                request.subjects() != null ? String.join(",", request.subjects()) : ""
        );
    }

    public Long getTechnicalId() { return technicalId; }
    public WorkId getId() { return id; }
    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public String getPublisher() { return publisher; }
    public Integer getYear() { return year; }
    public String getType() { return type; }
    public String getLanguage() { return language; }
    public String getDescription() { return description; }
    public List<String> getAuthors() { return List.of(authors.split(",")); }
    public List<String> getSubjects() {
        return subjects != null && !subjects.isBlank() ? List.of(subjects.split(",")) : List.of();
    }
}

