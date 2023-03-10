package org.taranco.booking.port.output;

import org.taranco.DomainEventPublisher;
import org.taranco.booking.dto.BookingCreatedEvent;

public interface ReservationEventPublisher extends DomainEventPublisher<BookingCreatedEvent> {
}