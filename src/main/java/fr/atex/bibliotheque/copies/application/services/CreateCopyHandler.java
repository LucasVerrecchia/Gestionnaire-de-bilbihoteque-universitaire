package fr.atex.bibliotheque.copies.application.services;

import fr.atex.bibliotheque.copies.application.gateways.CopyRepository;
import fr.atex.bibliotheque.copies.application.models.CreateCopyRequest;
import fr.atex.bibliotheque.copies.application.usecases.CreateCopy;
import fr.atex.bibliotheque.copies.domain.Copy;
import fr.atex.bibliotheque.copies.domain.CopyId;
import fr.atex.bibliotheque.shared.DomainIdGenerator;
import fr.atex.bibliotheque.shared.error.BusinessException;
import fr.atex.bibliotheque.works.application.gateways.WorkRepository;
import fr.atex.bibliotheque.works.domain.WorkId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class CreateCopyHandler implements CreateCopy {

    private static final Logger logger = LoggerFactory.getLogger(CreateCopyHandler.class);

    private final DomainIdGenerator idGenerator;
    private final CopyRepository copyRepository;
    private final WorkRepository workRepository;

    CreateCopyHandler(DomainIdGenerator idGenerator, CopyRepository copyRepository,
                      WorkRepository workRepository) {
        this.idGenerator = idGenerator;
        this.copyRepository = copyRepository;
        this.workRepository = workRepository;
    }

    @Transactional
    @Override
    public CopyId handle(WorkId workId, CreateCopyRequest request) {
        logger.info("Création d'un exemplaire pour l'œuvre : workId={}, barcode={}", workId.value(), request.barcode());

        if (workRepository.findById(workId).isEmpty()) {
            throw new java.util.NoSuchElementException("Œuvre introuvable : " + workId.value());
        }
        if (copyRepository.existsByBarcode(request.barcode())) {
            throw new BusinessException("Un exemplaire avec le code-barres " + request.barcode() + " existe déjà");
        }

        final var copyId = new CopyId(idGenerator.generate());
        final var copy = Copy.create(copyId, workId, request);
        copyRepository.save(copy);

        logger.debug("Exemplaire créé : id={}", copyId.value());
        return copyId;
    }
}

