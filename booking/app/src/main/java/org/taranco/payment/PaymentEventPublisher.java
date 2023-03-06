package org.taranco.payment;

import org.taranco.DomainEvent;

interface PaymentEventPublisher {
    void publish(DomainEvent event);
}
