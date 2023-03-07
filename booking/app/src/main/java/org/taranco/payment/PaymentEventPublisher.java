package org.taranco.payment;

import org.taranco.DomainEvent;
import org.taranco.DomainEventPublisher;

interface PaymentEventPublisher extends DomainEventPublisher {
    void publish(DomainEvent event);
}
