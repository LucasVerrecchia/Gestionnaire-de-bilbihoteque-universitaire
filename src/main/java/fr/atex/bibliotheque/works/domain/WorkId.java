package fr.atex.bibliotheque.works.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record WorkId(@Column(name = "work_id", nullable = false, unique = true) String value) {}

