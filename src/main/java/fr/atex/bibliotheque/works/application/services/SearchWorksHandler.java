package fr.atex.bibliotheque.works.application.services;

import fr.atex.bibliotheque.works.application.gateways.WorkRepository;
import fr.atex.bibliotheque.works.application.usecases.SearchWorks;
import fr.atex.bibliotheque.works.domain.Work;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
class SearchWorksHandler implements SearchWorks {

    private final WorkRepository workRepository;

    SearchWorksHandler(WorkRepository workRepository) {
        this.workRepository = workRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Work> handle(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return workRepository.findAll();
        }
        return workRepository.findByTitleContaining(keyword);
    }
}

