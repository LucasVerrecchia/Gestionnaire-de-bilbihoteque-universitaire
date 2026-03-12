package fr.atex.bibliotheque.copies.application.services;

import fr.atex.bibliotheque.copies.application.gateways.CopyRepository;
import fr.atex.bibliotheque.copies.application.usecases.GetCopiesByWork;
import fr.atex.bibliotheque.copies.domain.Copy;
import fr.atex.bibliotheque.works.domain.WorkId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
class GetCopiesByWorkHandler implements GetCopiesByWork {

    private final CopyRepository copyRepository;

    GetCopiesByWorkHandler(CopyRepository copyRepository) {
        this.copyRepository = copyRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Copy> handle(WorkId workId) {
        return copyRepository.findByWorkId(workId);
    }
}

