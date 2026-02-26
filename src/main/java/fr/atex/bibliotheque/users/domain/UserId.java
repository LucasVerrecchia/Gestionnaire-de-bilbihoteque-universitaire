package fr.atex.bibliotheque.users.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record UserId(@Column(name = "user_id", nullable = false, unique = true) String value) {}

