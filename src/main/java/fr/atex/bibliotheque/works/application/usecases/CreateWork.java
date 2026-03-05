package fr.atex.bibliotheque.works.application.usecases;

import fr.atex.bibliotheque.works.application.models.CreateWorkRequest;
import fr.atex.bibliotheque.works.domain.WorkId;

public interface CreateWork {
    WorkId handle(CreateWorkRequest request);
}

