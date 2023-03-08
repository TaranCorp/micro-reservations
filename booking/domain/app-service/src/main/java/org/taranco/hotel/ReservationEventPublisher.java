package org.taranco.hotel;

import org.taranco.DomainEventPublisher;
import org.taranco.booking.dto.BookingCreatedEvent;

public interface ReservationEventPublisher extends DomainEventPublisher<BookingCreatedEvent> {
}