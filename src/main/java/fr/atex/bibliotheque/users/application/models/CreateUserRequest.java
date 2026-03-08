package fr.atex.bibliotheque.users.application.models;

import fr.atex.bibliotheque.users.domain.UserCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Requête pour créer un nouveau lecteur")
public record CreateUserRequest(

        @Schema(description = "Prénom", example = "Marie")
        @NotBlank(message = "Le prénom est obligatoire")
        String firstName,

        @Schema(description = "Nom", example = "Dupont")
        @NotBlank(message = "Le nom est obligatoire")
        String lastName,

        @Schema(description = "Email universitaire", example = "marie.dupont@universite.fr")
        @NotBlank(message = "L'email est obligatoire")
        @Email(message = "L'email doit être valide")
        String email,

        @Schema(description = "Catégorie : STUDENT, TEACHER ou RESEARCHER", example = "STUDENT")
        @NotNull(message = "La catégorie est obligatoire")
        UserCategory category
) {}

