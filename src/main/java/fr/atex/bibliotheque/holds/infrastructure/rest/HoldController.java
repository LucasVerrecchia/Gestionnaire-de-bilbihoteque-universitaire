package fr.atex.bibliotheque.holds.infrastructure.rest;

import fr.atex.bibliotheque.holds.application.models.CreateHoldRequest;
import fr.atex.bibliotheque.holds.application.usecases.CancelHold;
import fr.atex.bibliotheque.holds.application.usecases.CreateHold;
import fr.atex.bibliotheque.holds.application.usecases.GetHoldsByUser;
import fr.atex.bibliotheque.holds.domain.HoldId;
import fr.atex.bibliotheque.holds.infrastructure.rest.dto.HoldDTO;
import fr.atex.bibliotheque.holds.infrastructure.rest.mapper.HoldMapper;
import fr.atex.bibliotheque.users.domain.UserId;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/holds")
@Tag(name = "Holds", description = "Gestion des réservations")
class HoldController {

    private final CreateHold createHold;
    private final CancelHold cancelHold;
    private final GetHoldsByUser getHoldsByUser;
    private final HoldMapper holdMapper;

    HoldController(CreateHold createHold, CancelHold cancelHold,
                   GetHoldsByUser getHoldsByUser, HoldMapper holdMapper) {
        this.createHold = createHold;
        this.cancelHold = cancelHold;
        this.getHoldsByUser = getHoldsByUser;
        this.holdMapper = holdMapper;
    }

    @PostMapping
    @Operation(summary = "Créer une réservation")
    ResponseEntity<Void> createHold(@Valid @RequestBody CreateHoldRequest request) {
        final var holdId = createHold.handle(request);
        return ResponseEntity.created(URI.create("/api/holds/" + holdId.value())).build();
    }

    @DeleteMapping("/{holdId}")
    @Operation(summary = "Annuler une réservation")
    ResponseEntity<Void> cancelHold(@PathVariable String holdId) {
        cancelHold.handle(new HoldId(holdId));
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(summary = "Lister les réservations d'un lecteur")
    ResponseEntity<List<HoldDTO>> getByUser(@RequestParam String userId) {
        return ResponseEntity.ok(holdMapper.toDTOList(getHoldsByUser.handle(new UserId(userId))));
    }
}

