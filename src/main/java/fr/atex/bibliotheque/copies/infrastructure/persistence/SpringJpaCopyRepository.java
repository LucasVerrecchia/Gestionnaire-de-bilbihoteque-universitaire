package fr.atex.bibliotheque.copies.infrastructure.persistence;

import fr.atex.bibliotheque.copies.domain.Copy;
import fr.atex.bibliotheque.copies.domain.CopyId;
import fr.atex.bibliotheque.copies.domain.CopyStatus;
import fr.atex.bibliotheque.works.domain.WorkId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpringJpaCopyRepository extends JpaRepository<Copy, Long> {
    Optional<Copy> findById(CopyId id);
    List<Copy> findByWorkId(WorkId workId);
    List<Copy> findByWorkIdAndStatus(WorkId workId, CopyStatus status);
    boolean existsByBarcode(String barcode);
}

