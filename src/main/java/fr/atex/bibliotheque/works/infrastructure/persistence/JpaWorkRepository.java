package fr.atex.bibliotheque.works.infrastructure.persistence;

import fr.atex.bibliotheque.works.application.gateways.WorkRepository;
import fr.atex.bibliotheque.works.domain.Work;
import fr.atex.bibliotheque.works.domain.WorkId;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
class JpaWorkRepository implements WorkRepository {

    private final SpringJpaWorkRepository jpaRepository;

    JpaWorkRepository(SpringJpaWorkRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void save(Work work) {
        jpaRepository.save(work);
    }

    @Override
    public Optional<Work> findById(WorkId id) {
        return jpaRepository.findById(id);
    }

    @Override
    public List<Work> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public List<Work> findByTitleContaining(String keyword) {
        return jpaRepository.findByTitleContainingIgnoreCase(keyword);
    }

    @Override
    public boolean existsByIsbn(String isbn) {
        return jpaRepository.existsByIsbn(isbn);
    }
}

