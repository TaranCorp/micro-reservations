package org.taranco.booking.port.output;

import org.taranco.DomainEventPublisher;
import org.taranco.booking.dto.BookingReservedEvent;

public interface PaymentEventPublisher extends DomainEventPublisher<BookingReservedEvent> {
}
