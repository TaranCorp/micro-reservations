package org.taranco.hotel;

import org.taranco.DomainEvent;

interface ReservationEventPublisher {
    void publish(DomainEvent event);
}
