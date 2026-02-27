package fr.atex.bibliotheque.copies.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record CopyId(@Column(name = "copy_id", nullable = false, unique = true) String value) {}

