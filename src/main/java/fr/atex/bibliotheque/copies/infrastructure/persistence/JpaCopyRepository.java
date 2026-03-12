package fr.atex.bibliotheque.copies.infrastructure.persistence;

import fr.atex.bibliotheque.copies.application.gateways.CopyRepository;
import fr.atex.bibliotheque.copies.domain.Copy;
import fr.atex.bibliotheque.copies.domain.CopyId;
import fr.atex.bibliotheque.copies.domain.CopyStatus;
import fr.atex.bibliotheque.works.domain.WorkId;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
class JpaCopyRepository implements CopyRepository {

    private final SpringJpaCopyRepository jpaRepository;

    JpaCopyRepository(SpringJpaCopyRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void save(Copy copy) {
        jpaRepository.save(copy);
    }

    @Override
    public Optional<Copy> findById(CopyId id) {
        return jpaRepository.findById(id);
    }

    @Override
    public List<Copy> findByWorkId(WorkId workId) {
        return jpaRepository.findByWorkId(workId);
    }

    @Override
    public List<Copy> findByWorkIdAndStatus(WorkId workId, CopyStatus status) {
        return jpaRepository.findByWorkIdAndStatus(workId, status);
    }

    @Override
    public boolean existsByBarcode(String barcode) {
        return jpaRepository.existsByBarcode(barcode);
    }
}

