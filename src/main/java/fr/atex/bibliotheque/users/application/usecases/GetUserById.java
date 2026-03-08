package fr.atex.bibliotheque.users.application.usecases;

import fr.atex.bibliotheque.users.domain.LibraryUser;
import fr.atex.bibliotheque.users.domain.UserId;

import java.util.Optional;

public interface GetUserById {
    Optional<LibraryUser> handle(UserId id);
}

