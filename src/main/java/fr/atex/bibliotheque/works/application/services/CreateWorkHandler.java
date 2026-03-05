package fr.atex.bibliotheque.works.application.services;

import fr.atex.bibliotheque.shared.error.BusinessException;
import fr.atex.bibliotheque.shared.DomainIdGenerator;
import fr.atex.bibliotheque.works.application.gateways.WorkRepository;
import fr.atex.bibliotheque.works.application.models.CreateWorkRequest;
import fr.atex.bibliotheque.works.application.usecases.CreateWork;
import fr.atex.bibliotheque.works.domain.Work;
import fr.atex.bibliotheque.works.domain.WorkId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class CreateWorkHandler implements CreateWork {

    private static final Logger logger = LoggerFactory.getLogger(CreateWorkHandler.class);

    private final DomainIdGenerator idGenerator;
    private final WorkRepository workRepository;

    CreateWorkHandler(DomainIdGenerator idGenerator, WorkRepository workRepository) {
        this.idGenerator = idGenerator;
        this.workRepository = workRepository;
    }

    @Transactional
    @Override
    public WorkId handle(CreateWorkRequest request) {
        logger.info("Création d'une nouvelle œuvre : isbn={}, title={}", request.isbn(), request.title());

        if (workRepository.existsByIsbn(request.isbn())) {
            throw new BusinessException("Une œuvre avec l'ISBN " + request.isbn() + " existe déjà");
        }

        final var workId = new WorkId(idGenerator.generate());
        final var work = Work.create(workId, request);
        workRepository.save(work);

        logger.debug("Œuvre créée avec succès : id={}, title={}", workId.value(), work.getTitle());
        return workId;
    }
}
