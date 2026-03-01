package fr.atex.bibliotheque.shared;

import java.time.Instant;

@FunctionalInterface
public interface TimeProvider {
    Instant now();
}

