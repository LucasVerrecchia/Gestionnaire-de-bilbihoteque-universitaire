package fr.atex.bibliotheque.users.infrastructure.persistence;

import fr.atex.bibliotheque.users.application.gateways.UserRepository;
import fr.atex.bibliotheque.users.domain.LibraryUser;
import fr.atex.bibliotheque.users.domain.UserId;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
class JpaUserRepository implements UserRepository {

    private final SpringJpaUserRepository jpaRepository;

    JpaUserRepository(SpringJpaUserRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void save(LibraryUser user) {
        jpaRepository.save(user);
    }

    @Override
    public Optional<LibraryUser> findById(UserId id) {
        return jpaRepository.findById(id);
    }

    @Override
    public List<LibraryUser> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaRepository.existsByEmail(email);
    }
}

