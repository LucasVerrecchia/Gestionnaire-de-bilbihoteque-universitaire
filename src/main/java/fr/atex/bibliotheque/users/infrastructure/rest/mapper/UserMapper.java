package fr.atex.bibliotheque.users.infrastructure.rest.mapper;

import fr.atex.bibliotheque.users.domain.LibraryUser;
import fr.atex.bibliotheque.users.infrastructure.rest.dto.UserDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {

    public UserDTO toDTO(LibraryUser user) {
        return new UserDTO(
                user.getId().value(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getCategory().name(),
                user.isBlocked(),
                user.getCreatedAt(),
                user.getMaxLoans(),
                user.getLoanDurationDays(),
                user.getMaxRenewals()
        );
    }

    public List<UserDTO> toDTOList(List<LibraryUser> users) {
        return users.stream().map(this::toDTO).toList();
    }
}

