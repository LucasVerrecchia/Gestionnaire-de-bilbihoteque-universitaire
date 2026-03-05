package fr.atex.bibliotheque.works.infrastructure.persistence;

import fr.atex.bibliotheque.works.domain.Work;
import fr.atex.bibliotheque.works.domain.WorkId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpringJpaWorkRepository extends JpaRepository<Work, Long> {
    Optional<Work> findById(WorkId id);
    boolean existsByIsbn(String isbn);
    List<Work> findByTitleContainingIgnoreCase(String keyword);
}

