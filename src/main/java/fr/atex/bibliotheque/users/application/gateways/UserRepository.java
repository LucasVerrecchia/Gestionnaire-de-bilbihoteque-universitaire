package fr.atex.bibliotheque.users.application.gateways;

import fr.atex.bibliotheque.users.domain.LibraryUser;
import fr.atex.bibliotheque.users.domain.UserId;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void save(LibraryUser user);
    Optional<LibraryUser> findById(UserId id);
    List<LibraryUser> findAll();
    boolean existsByEmail(String email);
}

