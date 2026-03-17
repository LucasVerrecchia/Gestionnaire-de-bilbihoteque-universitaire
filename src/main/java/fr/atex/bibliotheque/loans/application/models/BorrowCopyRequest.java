package fr.atex.bibliotheque.loans.application.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Requête pour emprunter un exemplaire")
public record BorrowCopyRequest(

        @Schema(description = "ID de l'exemplaire à emprunter", example = "uuid-de-l-exemplaire")
        @NotBlank(message = "L'ID de l'exemplaire est obligatoire")
        String copyId,

        @Schema(description = "ID du lecteur", example = "uuid-du-lecteur")
        @NotBlank(message = "L'ID du lecteur est obligatoire")
        String userId
) {}

