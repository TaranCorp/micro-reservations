package org.taranco.hotel;

import org.taranco.DomainEvent;
import org.taranco.DomainEventPublisher;

public interface BookingApproveEventPublisher extends DomainEventPublisher {
    void publish(DomainEvent domainEvent);
}
