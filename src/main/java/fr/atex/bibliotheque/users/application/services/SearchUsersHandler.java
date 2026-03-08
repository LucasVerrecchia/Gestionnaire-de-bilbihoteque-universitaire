package fr.atex.bibliotheque.users.application.services;

import fr.atex.bibliotheque.users.application.gateways.UserRepository;
import fr.atex.bibliotheque.users.application.usecases.SearchUsers;
import fr.atex.bibliotheque.users.domain.LibraryUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
class SearchUsersHandler implements SearchUsers {

    private final UserRepository userRepository;

    SearchUsersHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<LibraryUser> handle() {
        return userRepository.findAll();
    }
}

