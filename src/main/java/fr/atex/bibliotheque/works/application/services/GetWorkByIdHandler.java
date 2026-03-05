package fr.atex.bibliotheque.works.application.services;

import fr.atex.bibliotheque.works.application.gateways.WorkRepository;
import fr.atex.bibliotheque.works.application.usecases.GetWorkById;
import fr.atex.bibliotheque.works.domain.Work;
import fr.atex.bibliotheque.works.domain.WorkId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
class GetWorkByIdHandler implements GetWorkById {

    private final WorkRepository workRepository;

    GetWorkByIdHandler(WorkRepository workRepository) {
        this.workRepository = workRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Work> handle(WorkId id) {
        return workRepository.findById(id);
    }
}

