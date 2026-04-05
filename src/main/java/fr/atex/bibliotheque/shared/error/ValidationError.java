package fr.atex.bibliotheque.shared.error;

public record ValidationError(String field, Object rejectedValue, String message) {}

