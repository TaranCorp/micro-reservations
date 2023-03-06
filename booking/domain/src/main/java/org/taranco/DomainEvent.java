package org.taranco;

import java.time.Instant;

public interface DomainEvent {
    Instant occurredOn();
}
