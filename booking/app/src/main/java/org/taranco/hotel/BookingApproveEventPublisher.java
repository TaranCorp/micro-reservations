package org.taranco.hotel;

import org.taranco.DomainEvent;

interface BookingApproveEventPublisher {
    void publish(DomainEvent domainEvent);
}
