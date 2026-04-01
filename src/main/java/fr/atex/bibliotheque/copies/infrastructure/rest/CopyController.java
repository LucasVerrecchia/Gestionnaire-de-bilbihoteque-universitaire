package fr.atex.bibliotheque.copies.infrastructure.rest;

import fr.atex.bibliotheque.copies.application.models.CreateCopyRequest;
import fr.atex.bibliotheque.copies.application.usecases.CreateCopy;
import fr.atex.bibliotheque.copies.application.usecases.GetCopiesByWork;
import fr.atex.bibliotheque.copies.infrastructure.rest.dto.CopyDTO;
import fr.atex.bibliotheque.copies.infrastructure.rest.mapper.CopyMapper;
import fr.atex.bibliotheque.works.domain.WorkId;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/works/{workId}/copies")
@Tag(name = "Copies", description = "Gestion des exemplaires")
class CopyController {

    private final CreateCopy createCopy;
    private final GetCopiesByWork getCopiesByWork;
    private final CopyMapper copyMapper;

    CopyController(CreateCopy createCopy, GetCopiesByWork getCopiesByWork, CopyMapper copyMapper) {
        this.createCopy = createCopy;
        this.getCopiesByWork = getCopiesByWork;
        this.copyMapper = copyMapper;
    }

    @GetMapping
    @Operation(summary = "Lister les exemplaires d'une œuvre")
    ResponseEntity<List<CopyDTO>> getCopies(@PathVariable String workId) {
        return ResponseEntity.ok(copyMapper.toDTOList(getCopiesByWork.handle(new WorkId(workId))));
    }

    @PostMapping
    @Operation(summary = "Ajouter un exemplaire à une œuvre")
    ResponseEntity<Void> createCopy(
            @PathVariable String workId,
            @Valid @RequestBody CreateCopyRequest request) {
        final var copyId = createCopy.handle(new WorkId(workId), request);
        return ResponseEntity.created(
                URI.create("/api/works/" + workId + "/copies/" + copyId.value())).build();
    }
}

