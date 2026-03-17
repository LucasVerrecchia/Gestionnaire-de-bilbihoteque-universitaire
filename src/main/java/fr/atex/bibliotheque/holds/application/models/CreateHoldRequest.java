package fr.atex.bibliotheque.holds.application.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Requête pour réserver une œuvre")
public record CreateHoldRequest(

        @Schema(description = "ID de l'œuvre à réserver", example = "uuid-de-l-oeuvre")
        @NotBlank(message = "L'ID de l'œuvre est obligatoire")
        String workId,

        @Schema(description = "ID du lecteur", example = "uuid-du-lecteur")
        @NotBlank(message = "L'ID du lecteur est obligatoire")
        String userId
) {}

