package fr.atex.bibliotheque.works.infrastructure.rest.mapper;

import fr.atex.bibliotheque.works.domain.Work;
import fr.atex.bibliotheque.works.infrastructure.rest.dto.WorkDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WorkMapper {

    public WorkDTO toDTO(Work work) {
        return new WorkDTO(
                work.getId().value(),
                work.getIsbn(),
                work.getTitle(),
                work.getAuthors(),
                work.getPublisher(),
                work.getYear(),
                work.getType(),
                work.getLanguage(),
                work.getDescription(),
                work.getSubjects()
        );
    }

    public List<WorkDTO> toDTOList(List<Work> works) {
        return works.stream().map(this::toDTO).toList();
    }
}

