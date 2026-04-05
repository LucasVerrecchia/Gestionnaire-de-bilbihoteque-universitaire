package fr.atex.bibliotheque.shared.error;

import java.util.List;

public record ErrorResponse(
        int status,
        String error,
        String message,
        String path,
        List<ValidationError> validationErrors
) {
    public ErrorResponse(int status, String error, String message, String path) {
        this(status, error, message, path, List.of());
    }
}

