package fr.atex.bibliotheque.works.infrastructure.rest.dto;

import java.util.List;

public record WorkDTO(
        String id,
        String isbn,
        String title,
        List<String> authors,
        String publisher,
        Integer year,
        String type,
        String language,
        String description,
        List<String> subjects
) {}

