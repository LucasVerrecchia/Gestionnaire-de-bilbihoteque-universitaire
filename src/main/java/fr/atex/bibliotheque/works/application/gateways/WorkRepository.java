package fr.atex.bibliotheque.works.application.gateways;

import fr.atex.bibliotheque.works.domain.Work;
import fr.atex.bibliotheque.works.domain.WorkId;

import java.util.List;
import java.util.Optional;

public interface WorkRepository {
    void save(Work work);
    Optional<Work> findById(WorkId id);
    List<Work> findAll();
    List<Work> findByTitleContaining(String keyword);
    boolean existsByIsbn(String isbn);
}

