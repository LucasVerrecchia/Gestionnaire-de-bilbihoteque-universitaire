package fr.atex.bibliotheque.copies.application.usecases;

import fr.atex.bibliotheque.copies.application.models.CreateCopyRequest;
import fr.atex.bibliotheque.copies.domain.CopyId;
import fr.atex.bibliotheque.works.domain.WorkId;

public interface CreateCopy {
    CopyId handle(WorkId workId, CreateCopyRequest request);
}

