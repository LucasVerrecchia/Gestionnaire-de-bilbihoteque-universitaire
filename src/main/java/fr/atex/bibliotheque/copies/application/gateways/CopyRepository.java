package fr.atex.bibliotheque.copies.application.gateways;

import fr.atex.bibliotheque.copies.domain.Copy;
import fr.atex.bibliotheque.copies.domain.CopyId;
import fr.atex.bibliotheque.copies.domain.CopyStatus;
import fr.atex.bibliotheque.works.domain.WorkId;

import java.util.List;
import java.util.Optional;

public interface CopyRepository {
    void save(Copy copy);
    Optional<Copy> findById(CopyId id);
    List<Copy> findByWorkId(WorkId workId);
    List<Copy> findByWorkIdAndStatus(WorkId workId, CopyStatus status);
    boolean existsByBarcode(String barcode);
}

