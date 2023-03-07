package org.taranco.hotel;

import org.taranco.DomainEvent;

interface BookingCancellationEventPublisher {
    void publish(DomainEvent event);
}
