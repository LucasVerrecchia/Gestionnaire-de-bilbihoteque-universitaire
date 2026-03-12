package fr.atex.bibliotheque.copies.application.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Schema(description = "Requête pour créer un nouvel exemplaire")
public record CreateCopyRequest(

        @Schema(description = "Code-barres de l'exemplaire", example = "BC-2024-001")
        @NotBlank(message = "Le code-barres est obligatoire")
        String barcode,

        @Schema(description = "Campus de localisation", example = "Campus Central")
        @NotBlank(message = "Le campus est obligatoire")
        String campus,

        @Schema(description = "Rayon / localisation précise", example = "A-42")
        String shelf,

        @Schema(description = "État de l'exemplaire : GOOD, WORN, DAMAGED", example = "GOOD")
        @NotBlank(message = "L'état est obligatoire")
        @Pattern(regexp = "GOOD|WORN|DAMAGED", message = "L'état doit être GOOD, WORN ou DAMAGED")
        String condition
) {}

