package fr.atex.bibliotheque.holds.infrastructure.rest.mapper;

import fr.atex.bibliotheque.holds.domain.Hold;
import fr.atex.bibliotheque.holds.infrastructure.rest.dto.HoldDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HoldMapper {

    public HoldDTO toDTO(Hold hold) {
        return new HoldDTO(
                hold.getId().value(),
                hold.getWorkId().value(),
                hold.getUserId().value(),
                hold.getRequestedAt(),
                hold.getReadyAt(),
                hold.getExpiresAt(),
                hold.getStatus().name()
        );
    }

    public List<HoldDTO> toDTOList(List<Hold> holds) {
        return holds.stream().map(this::toDTO).toList();
    }
}

