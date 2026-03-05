package fr.atex.bibliotheque.works.application.usecases;

import fr.atex.bibliotheque.works.domain.Work;

import java.util.List;

public interface SearchWorks {
    List<Work> handle(String keyword);
}

