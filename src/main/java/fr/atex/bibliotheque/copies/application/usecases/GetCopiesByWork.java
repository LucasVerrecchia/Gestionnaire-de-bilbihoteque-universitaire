package fr.atex.bibliotheque.copies.application.usecases;

import fr.atex.bibliotheque.copies.domain.Copy;
import fr.atex.bibliotheque.works.domain.WorkId;

import java.util.List;

public interface GetCopiesByWork {
    List<Copy> handle(WorkId workId);
}

