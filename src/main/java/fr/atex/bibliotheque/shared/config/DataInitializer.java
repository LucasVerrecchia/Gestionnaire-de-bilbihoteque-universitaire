package fr.atex.bibliotheque.shared.config;

import fr.atex.bibliotheque.copies.application.models.CreateCopyRequest;
import fr.atex.bibliotheque.copies.application.usecases.CreateCopy;
import fr.atex.bibliotheque.copies.domain.CopyId;
import fr.atex.bibliotheque.holds.application.models.CreateHoldRequest;
import fr.atex.bibliotheque.holds.application.usecases.CreateHold;
import fr.atex.bibliotheque.loans.application.models.BorrowCopyRequest;
import fr.atex.bibliotheque.loans.application.usecases.BorrowCopy;
import fr.atex.bibliotheque.users.application.models.CreateUserRequest;
import fr.atex.bibliotheque.users.application.usecases.CreateUser;
import fr.atex.bibliotheque.users.domain.UserCategory;
import fr.atex.bibliotheque.users.domain.UserId;
import fr.atex.bibliotheque.works.application.models.CreateWorkRequest;
import fr.atex.bibliotheque.works.application.usecases.CreateWork;
import fr.atex.bibliotheque.works.domain.WorkId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("dev")
public class DataInitializer {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    @Bean
    ApplicationRunner seedDatabase(
            CreateWork createWork,
            CreateUser createUser,
            CreateCopy createCopy,
            BorrowCopy borrowCopy,
            CreateHold createHold
    ) {
        return args -> {
            log.info("=== Initialisation des données de démonstration ===");

            // --- Œuvres ---
            WorkId wLePetitPrince = createWork.handle(new CreateWorkRequest(
                    "978-2-07-040850-4", "Le Petit Prince",
                    List.of("Antoine de Saint-Exupéry"), "Gallimard", 1943,
                    "BOOK", "fr",
                    "Un pilote échoué dans le désert rencontre un mystérieux petit prince venu d'une autre planète.",
                    List.of("littérature", "philosophie", "jeunesse")));

            WorkId wCleanCode = createWork.handle(new CreateWorkRequest(
                    "978-0-13-235088-4", "Clean Code",
                    List.of("Robert C. Martin"), "Prentice Hall", 2008,
                    "BOOK", "en",
                    "A handbook of agile software craftsmanship for writing readable and maintainable code.",
                    List.of("informatique", "génie logiciel", "bonnes pratiques")));

            WorkId wAlgorithmes = createWork.handle(new CreateWorkRequest(
                    "978-2-10-007346-1", "Introduction aux algorithmes",
                    List.of("Thomas H. Cormen", "Charles E. Leiserson", "Ronald L. Rivest", "Clifford Stein"),
                    "Dunod", 2010,
                    "BOOK", "fr",
                    "La référence en algorithmique, couvrant les structures de données et les algorithmes fondamentaux.",
                    List.of("informatique", "algorithmique", "mathématiques")));

            WorkId wLesMiserables = createWork.handle(new CreateWorkRequest(
                    "978-2-07-040327-1", "Les Misérables",
                    List.of("Victor Hugo"), "Gallimard", 1862,
                    "BOOK", "fr",
                    "Un chef-d'œuvre de la littérature française, épopée sociale et humaniste dans la France du XIXe siècle.",
                    List.of("littérature", "roman", "histoire")));

            WorkId wDesignPatterns = createWork.handle(new CreateWorkRequest(
                    "978-0-20-163361-5", "Design Patterns",
                    List.of("Erich Gamma", "Richard Helm", "Ralph Johnson", "John Vlissides"),
                    "Addison-Wesley", 1994,
                    "BOOK", "en",
                    "Catalogue des 23 patrons de conception orientés objet devenus une référence incontournable.",
                    List.of("informatique", "architecture logicielle", "patrons de conception")));

            WorkId wTheseIA = createWork.handle(new CreateWorkRequest(
                    "978-0-00-000000-1", "Intelligence Artificielle et Éducation",
                    List.of("Jean-Paul Moreau"), "Université Paris-Saclay", 2023,
                    "THESIS", "fr",
                    "Thèse de doctorat analysant l'impact des outils d'IA sur les pratiques pédagogiques universitaires.",
                    List.of("intelligence artificielle", "éducation", "pédagogie")));

            log.info("6 œuvres créées.");

            // --- Utilisateurs ---
            UserId uMarie = createUser.handle(new CreateUserRequest(
                    "Marie", "Dupont", "marie.dupont@universite.fr", UserCategory.STUDENT));

            UserId uPierre = createUser.handle(new CreateUserRequest(
                    "Pierre", "Martin", "pierre.martin@universite.fr", UserCategory.STUDENT));

            UserId uSophie = createUser.handle(new CreateUserRequest(
                    "Sophie", "Bernard", "sophie.bernard@universite.fr", UserCategory.TEACHER));

            UserId uLucas = createUser.handle(new CreateUserRequest(
                    "Lucas", "Petit", "lucas.petit@universite.fr", UserCategory.RESEARCHER));

            UserId uEmma = createUser.handle(new CreateUserRequest(
                    "Emma", "Leblanc", "emma.leblanc@universite.fr", UserCategory.STUDENT));

            log.info("5 utilisateurs créés.");

            // --- Exemplaires ---
            CopyId cPrince1 = createCopy.handle(wLePetitPrince, new CreateCopyRequest("BC-2024-001", "Campus Central", "A-12", "GOOD"));
            CopyId cPrince2 = createCopy.handle(wLePetitPrince, new CreateCopyRequest("BC-2024-002", "Campus Nord", "B-05", "WORN"));

            CopyId cClean1 = createCopy.handle(wCleanCode, new CreateCopyRequest("BC-2024-003", "Campus Central", "C-08", "GOOD"));
            CopyId cClean2 = createCopy.handle(wCleanCode, new CreateCopyRequest("BC-2024-004", "Campus Central", "C-09", "GOOD"));

            CopyId cAlgo1 = createCopy.handle(wAlgorithmes, new CreateCopyRequest("BC-2024-005", "Campus Central", "C-15", "GOOD"));
            CopyId cAlgo2 = createCopy.handle(wAlgorithmes, new CreateCopyRequest("BC-2024-006", "Campus Sud", "D-03", "WORN"));
            createCopy.handle(wAlgorithmes, new CreateCopyRequest("BC-2024-007", "Campus Nord", "B-11", "GOOD"));

            createCopy.handle(wLesMiserables, new CreateCopyRequest("BC-2024-008", "Campus Central", "A-20", "GOOD"));

            createCopy.handle(wDesignPatterns, new CreateCopyRequest("BC-2024-009", "Campus Central", "C-22", "GOOD"));
            createCopy.handle(wDesignPatterns, new CreateCopyRequest("BC-2024-010", "Campus Est", "E-01", "DAMAGED"));

            createCopy.handle(wTheseIA, new CreateCopyRequest("BC-2024-011", "Campus Central", "T-01", "GOOD"));

            log.info("11 exemplaires créés.");

            // --- Emprunts ---
            borrowCopy.handle(new BorrowCopyRequest(cClean1.value(), uMarie.value()));
            borrowCopy.handle(new BorrowCopyRequest(cAlgo1.value(), uPierre.value()));
            borrowCopy.handle(new BorrowCopyRequest(cPrince1.value(), uSophie.value()));

            log.info("3 emprunts créés.");

            // --- Réservations ---
            createHold.handle(new CreateHoldRequest(wCleanCode.value(), uEmma.value()));
            createHold.handle(new CreateHoldRequest(wAlgorithmes.value(), uLucas.value()));

            log.info("2 réservations créées.");
            log.info("=== Données de démonstration chargées avec succès ! ===");
        };
    }
}
