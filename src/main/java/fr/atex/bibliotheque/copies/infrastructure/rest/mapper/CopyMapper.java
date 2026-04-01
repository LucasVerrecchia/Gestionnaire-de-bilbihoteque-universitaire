package fr.atex.bibliotheque.copies.infrastructure.rest.mapper;

import fr.atex.bibliotheque.copies.domain.Copy;
import fr.atex.bibliotheque.copies.infrastructure.rest.dto.CopyDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CopyMapper {

    public CopyDTO toDTO(Copy copy) {
        return new CopyDTO(
                copy.getId().value(),
                copy.getWorkId().value(),
                copy.getBarcode(),
                copy.getCampus(),
                copy.getShelf(),
                copy.getCondition(),
                copy.getStatus().name()
        );
    }

    public List<CopyDTO> toDTOList(List<Copy> copies) {
        return copies.stream().map(this::toDTO).toList();
    }
}

