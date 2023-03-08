package org.taranco;

public interface DomainEventPublisher<T extends DomainEvent> {
    void publish(T event);
}
