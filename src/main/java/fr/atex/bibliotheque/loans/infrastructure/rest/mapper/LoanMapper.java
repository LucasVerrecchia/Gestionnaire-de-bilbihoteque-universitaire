package fr.atex.bibliotheque.loans.infrastructure.rest.mapper;

import fr.atex.bibliotheque.loans.domain.Loan;
import fr.atex.bibliotheque.loans.infrastructure.rest.dto.LoanDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoanMapper {

    public LoanDTO toDTO(Loan loan) {
        return new LoanDTO(
                loan.getId().value(),
                loan.getCopyId().value(),
                loan.getUserId().value(),
                loan.getStartAt(),
                loan.getDueAt(),
                loan.getReturnedAt(),
                loan.getRenewCount(),
                loan.getStatus().name()
        );
    }

    public List<LoanDTO> toDTOList(List<Loan> loans) {
        return loans.stream().map(this::toDTO).toList();
    }
}

