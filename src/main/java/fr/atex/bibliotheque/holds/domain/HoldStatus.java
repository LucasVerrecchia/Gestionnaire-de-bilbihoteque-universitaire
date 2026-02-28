package fr.atex.bibliotheque.holds.domain;

public enum HoldStatus {
    PENDING,   // En attente d'un exemplaire disponible
    READY,     // Exemplaire disponible, attente de retrait
    FULFILLED, // Retiré (emprunt créé)
    CANCELLED, // Annulé par le lecteur ou expiré
    EXPIRED    // Non retiré dans le délai imparti
}

