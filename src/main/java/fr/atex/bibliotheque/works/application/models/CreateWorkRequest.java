package fr.atex.bibliotheque.works.application.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.util.List;

@Schema(description = "Requête pour créer une nouvelle œuvre dans le catalogue")
public record CreateWorkRequest(

        @Schema(description = "Numéro ISBN de l'œuvre", example = "978-2-07-036822-8")
        @NotBlank(message = "L'ISBN est obligatoire")
        String isbn,

        @Schema(description = "Titre de l'œuvre", example = "Le Petit Prince")
        @NotBlank(message = "Le titre est obligatoire")
        @Size(min = 1, max = 255, message = "Le titre doit faire entre 1 et 255 caractères")
        String title,

        @Schema(description = "Liste des auteurs", example = "[\"Antoine de Saint-Exupéry\"]")
        @NotEmpty(message = "Au moins un auteur est requis")
        List<String> authors,

        @Schema(description = "Éditeur", example = "Gallimard")
        @NotBlank(message = "L'éditeur est obligatoire")
        String publisher,

        @Schema(description = "Année de publication", example = "1943")
        @NotNull(message = "L'année est obligatoire")
        @Min(value = 1000, message = "L'année doit être valide")
        @Max(value = 2100, message = "L'année doit être valide")
        Integer year,

        @Schema(description = "Type de document : BOOK, THESIS, JOURNAL, DVD", example = "BOOK")
        @NotBlank(message = "Le type est obligatoire")
        @Pattern(regexp = "BOOK|THESIS|JOURNAL|DVD", message = "Le type doit être BOOK, THESIS, JOURNAL ou DVD")
        String type,

        @Schema(description = "Langue de l'œuvre", example = "fr")
        @NotBlank(message = "La langue est obligatoire")
        String language,

        @Schema(description = "Description / résumé", example = "Un classique de la littérature française")
        String description,

        @Schema(description = "Sujets / mots-clés", example = "[\"littérature\", \"philosophie\"]")
        List<String> subjects
) {}

