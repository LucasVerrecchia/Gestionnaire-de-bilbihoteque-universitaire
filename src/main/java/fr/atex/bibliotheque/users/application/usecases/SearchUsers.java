package fr.atex.bibliotheque.users.application.usecases;

import fr.atex.bibliotheque.users.domain.LibraryUser;

import java.util.List;

public interface SearchUsers {
    List<LibraryUser> handle();
}

