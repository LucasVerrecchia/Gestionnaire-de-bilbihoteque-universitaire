package fr.atex.bibliotheque.holds.infrastructure.rest.dto;

import java.time.Instant;

public record HoldDTO(
        String id,
        String workId,
        String userId,
        Instant requestedAt,
        Instant readyAt,
        Instant expiresAt,
        String status
) {}

