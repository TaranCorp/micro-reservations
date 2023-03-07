package org.taranco.hotel;

import org.taranco.DomainEvent;
import org.taranco.DomainEventPublisher;

interface ReservationEventPublisher extends DomainEventPublisher {
    void publish(DomainEvent event);
}
