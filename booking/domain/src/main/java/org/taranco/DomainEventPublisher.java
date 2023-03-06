package org.taranco;

public interface DomainEventPublisher {
    void publish(DomainEvent event);
}
