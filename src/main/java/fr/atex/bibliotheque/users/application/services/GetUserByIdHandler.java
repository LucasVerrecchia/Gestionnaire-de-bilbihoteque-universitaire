package fr.atex.bibliotheque.users.application.services;

import fr.atex.bibliotheque.users.application.gateways.UserRepository;
import fr.atex.bibliotheque.users.application.usecases.GetUserById;
import fr.atex.bibliotheque.users.domain.LibraryUser;
import fr.atex.bibliotheque.users.domain.UserId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
class GetUserByIdHandler implements GetUserById {

    private final UserRepository userRepository;

    GetUserByIdHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<LibraryUser> handle(UserId id) {
        return userRepository.findById(id);
    }
}

