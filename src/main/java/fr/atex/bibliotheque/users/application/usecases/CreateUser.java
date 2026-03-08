package fr.atex.bibliotheque.users.application.usecases;

import fr.atex.bibliotheque.users.application.models.CreateUserRequest;
import fr.atex.bibliotheque.users.domain.UserId;

public interface CreateUser {
    UserId handle(CreateUserRequest request);
}

