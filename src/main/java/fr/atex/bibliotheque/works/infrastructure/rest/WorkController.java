package fr.atex.bibliotheque.works.infrastructure.rest;

import fr.atex.bibliotheque.works.application.models.CreateWorkRequest;
import fr.atex.bibliotheque.works.application.usecases.CreateWork;
import fr.atex.bibliotheque.works.application.usecases.GetWorkById;
import fr.atex.bibliotheque.works.application.usecases.SearchWorks;
import fr.atex.bibliotheque.works.domain.WorkId;
import fr.atex.bibliotheque.works.infrastructure.rest.dto.WorkDTO;
import fr.atex.bibliotheque.works.infrastructure.rest.mapper.WorkMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/works")
@Tag(name = "Works", description = "Gestion du catalogue des œuvres")
class WorkController {

    private final CreateWork createWork;
    private final SearchWorks searchWorks;
    private final GetWorkById getWorkById;
    private final WorkMapper workMapper;

    WorkController(CreateWork createWork, SearchWorks searchWorks,
                   GetWorkById getWorkById, WorkMapper workMapper) {
        this.createWork = createWork;
        this.searchWorks = searchWorks;
        this.getWorkById = getWorkById;
        this.workMapper = workMapper;
    }

    @GetMapping
    @Operation(summary = "Rechercher des œuvres", description = "Retourne toutes les œuvres ou filtre par mot-clé dans le titre")
    ResponseEntity<List<WorkDTO>> searchWorks(@RequestParam(required = false) String keyword) {
        return ResponseEntity.ok(workMapper.toDTOList(searchWorks.handle(keyword)));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer une œuvre par son ID")
    ResponseEntity<WorkDTO> getWork(@PathVariable String id) {
        return getWorkById.handle(new WorkId(id))
                .map(workMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Créer une nouvelle œuvre dans le catalogue")
    ResponseEntity<Void> createWork(@Valid @RequestBody CreateWorkRequest request) {
        final var workId = createWork.handle(request);
        return ResponseEntity.created(URI.create("/api/works/" + workId.value())).build();
    }
}

