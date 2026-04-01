package fr.atex.bibliotheque.loans.infrastructure.rest.dto;

import java.time.Instant;

public record LoanDTO(
        String id,
        String copyId,
        String userId,
        Instant startAt,
        Instant dueAt,
        Instant returnedAt,
        int renewCount,
        String status
) {}

