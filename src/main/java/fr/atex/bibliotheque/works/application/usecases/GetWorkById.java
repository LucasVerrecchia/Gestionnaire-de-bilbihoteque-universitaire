package fr.atex.bibliotheque.works.application.usecases;

import fr.atex.bibliotheque.works.domain.Work;
import fr.atex.bibliotheque.works.domain.WorkId;

import java.util.Optional;

public interface GetWorkById {
    Optional<Work> handle(WorkId id);
}

