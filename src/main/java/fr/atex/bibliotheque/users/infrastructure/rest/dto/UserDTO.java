package fr.atex.bibliotheque.users.infrastructure.rest.dto;

import java.time.Instant;

public record UserDTO(
        String id,
        String firstName,
        String lastName,
        String email,
        String category,
        boolean blocked,
        Instant createdAt,
        int maxLoans,
        int loanDurationDays,
        int maxRenewals
) {}

