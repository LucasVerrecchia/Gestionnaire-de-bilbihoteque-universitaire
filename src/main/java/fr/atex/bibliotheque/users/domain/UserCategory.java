package fr.atex.bibliotheque.users.domain;

public enum UserCategory {
    STUDENT,   // Max 5 emprunts / 21 jours / 2 prolongations
    TEACHER,   // Max 20 emprunts / 60 jours / 3 prolongations
    RESEARCHER // Idem enseignant
}

