package fr.atex.bibliotheque.users.infrastructure.rest;

import fr.atex.bibliotheque.users.application.models.CreateUserRequest;
import fr.atex.bibliotheque.users.application.usecases.CreateUser;
import fr.atex.bibliotheque.users.application.usecases.GetUserById;
import fr.atex.bibliotheque.users.application.usecases.SearchUsers;
import fr.atex.bibliotheque.users.domain.UserId;
import fr.atex.bibliotheque.users.infrastructure.rest.dto.UserDTO;
import fr.atex.bibliotheque.users.infrastructure.rest.mapper.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Users", description = "Gestion des lecteurs")
class UserController {

    private final CreateUser createUser;
    private final GetUserById getUserById;
    private final SearchUsers searchUsers;
    private final UserMapper userMapper;

    UserController(CreateUser createUser, GetUserById getUserById,
                   SearchUsers searchUsers, UserMapper userMapper) {
        this.createUser = createUser;
        this.getUserById = getUserById;
        this.searchUsers = searchUsers;
        this.userMapper = userMapper;
    }

    @GetMapping
    @Operation(summary = "Lister tous les lecteurs")
    ResponseEntity<List<UserDTO>> listUsers() {
        return ResponseEntity.ok(userMapper.toDTOList(searchUsers.handle()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un lecteur par son ID")
    ResponseEntity<UserDTO> getUser(@PathVariable String id) {
        return getUserById.handle(new UserId(id))
                .map(userMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Créer un nouveau lecteur")
    ResponseEntity<Void> createUser(@Valid @RequestBody CreateUserRequest request) {
        final var userId = createUser.handle(request);
        return ResponseEntity.created(URI.create("/api/users/" + userId.value())).build();
    }
}

