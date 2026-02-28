package fr.atex.bibliotheque.holds.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record HoldId(@Column(name = "hold_id", nullable = false, unique = true) String value) {}

