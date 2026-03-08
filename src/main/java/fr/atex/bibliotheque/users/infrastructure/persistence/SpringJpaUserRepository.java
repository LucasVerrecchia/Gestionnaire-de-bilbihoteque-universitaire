package fr.atex.bibliotheque.users.infrastructure.persistence;

import fr.atex.bibliotheque.users.domain.LibraryUser;
import fr.atex.bibliotheque.users.domain.UserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringJpaUserRepository extends JpaRepository<LibraryUser, Long> {
    Optional<LibraryUser> findById(UserId id);
    boolean existsByEmail(String email);
}

