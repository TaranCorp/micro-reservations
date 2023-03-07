package org.taranco.hotel;

import org.taranco.DomainEvent;
import org.taranco.DomainEventPublisher;

public interface ReservationEventPublisher extends DomainEventPublisher {
    void publish(DomainEvent event);
}