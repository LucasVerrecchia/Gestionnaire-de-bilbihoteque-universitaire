package fr.atex.bibliotheque.shared;

import java.time.Clock;
import java.time.Instant;

final class SystemUtcTimeProvider implements TimeProvider {

    private final Clock clock;

    SystemUtcTimeProvider(Clock clock) {
        this.clock = clock;
    }

    @Override
    public Instant now() {
        return Instant.now(clock);
    }
}
