package fr.atex.bibliotheque.loans.infrastructure.rest;

import fr.atex.bibliotheque.loans.application.models.BorrowCopyRequest;
import fr.atex.bibliotheque.loans.application.usecases.BorrowCopy;
import fr.atex.bibliotheque.loans.application.usecases.GetLoansByUser;
import fr.atex.bibliotheque.loans.application.usecases.RenewLoan;
import fr.atex.bibliotheque.loans.application.usecases.ReturnCopy;
import fr.atex.bibliotheque.loans.domain.LoanId;
import fr.atex.bibliotheque.loans.infrastructure.rest.dto.LoanDTO;
import fr.atex.bibliotheque.loans.infrastructure.rest.mapper.LoanMapper;
import fr.atex.bibliotheque.users.domain.UserId;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/loans")
@Tag(name = "Loans", description = "Gestion des emprunts")
class LoanController {

    private final BorrowCopy borrowCopy;
    private final ReturnCopy returnCopy;
    private final RenewLoan renewLoan;
    private final GetLoansByUser getLoansByUser;
    private final LoanMapper loanMapper;

    LoanController(BorrowCopy borrowCopy, ReturnCopy returnCopy, RenewLoan renewLoan,
                   GetLoansByUser getLoansByUser, LoanMapper loanMapper) {
        this.borrowCopy = borrowCopy;
        this.returnCopy = returnCopy;
        this.renewLoan = renewLoan;
        this.getLoansByUser = getLoansByUser;
        this.loanMapper = loanMapper;
    }

    @PostMapping
    @Operation(summary = "Emprunter un exemplaire")
    ResponseEntity<Void> borrow(@Valid @RequestBody BorrowCopyRequest request) {
        final var loanId = borrowCopy.handle(request);
        return ResponseEntity.created(URI.create("/api/loans/" + loanId.value())).build();
    }

    @PatchMapping("/{loanId}/return")
    @Operation(summary = "Retourner un exemplaire")
    ResponseEntity<Void> returnLoan(@PathVariable String loanId) {
        returnCopy.handle(new LoanId(loanId));
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{loanId}/renew")
    @Operation(summary = "Prolonger un emprunt")
    ResponseEntity<Void> renew(@PathVariable String loanId) {
        renewLoan.handle(new LoanId(loanId));
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(summary = "Lister les emprunts d'un lecteur")
    ResponseEntity<List<LoanDTO>> getByUser(@RequestParam String userId) {
        return ResponseEntity.ok(loanMapper.toDTOList(getLoansByUser.handle(new UserId(userId))));
    }
}

