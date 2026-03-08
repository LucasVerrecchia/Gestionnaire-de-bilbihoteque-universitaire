package fr.atex.bibliotheque.users.application.services;

import fr.atex.bibliotheque.shared.DomainIdGenerator;
import fr.atex.bibliotheque.shared.TimeProvider;
import fr.atex.bibliotheque.shared.error.BusinessException;
import fr.atex.bibliotheque.users.application.gateways.UserRepository;
import fr.atex.bibliotheque.users.application.models.CreateUserRequest;
import fr.atex.bibliotheque.users.application.usecases.CreateUser;
import fr.atex.bibliotheque.users.domain.LibraryUser;
import fr.atex.bibliotheque.users.domain.UserId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class CreateUserHandler implements CreateUser {

    private static final Logger logger = LoggerFactory.getLogger(CreateUserHandler.class);

    private final DomainIdGenerator idGenerator;
    private final TimeProvider timeProvider;
    private final UserRepository userRepository;

    CreateUserHandler(DomainIdGenerator idGenerator, TimeProvider timeProvider,
                      UserRepository userRepository) {
        this.idGenerator = idGenerator;
        this.timeProvider = timeProvider;
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public UserId handle(CreateUserRequest request) {
        logger.info("Création d'un lecteur : email={}", request.email());

        if (userRepository.existsByEmail(request.email())) {
            throw new BusinessException("Un lecteur avec l'email " + request.email() + " existe déjà");
        }

        final var userId = new UserId(idGenerator.generate());
        final var user = LibraryUser.create(userId, request, timeProvider.now());
        userRepository.save(user);

        logger.debug("Lecteur créé : id={}", userId.value());
        return userId;
    }
}

