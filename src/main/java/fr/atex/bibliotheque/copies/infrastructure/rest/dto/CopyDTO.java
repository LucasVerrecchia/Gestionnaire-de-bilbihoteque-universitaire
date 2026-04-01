package fr.atex.bibliotheque.copies.infrastructure.rest.dto;

public record CopyDTO(
        String id,
        String workId,
        String barcode,
        String campus,
        String shelf,
        String condition,
        String status
) {}

