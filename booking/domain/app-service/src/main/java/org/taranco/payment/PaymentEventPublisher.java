package org.taranco.payment;

import org.taranco.DomainEventPublisher;
import org.taranco.booking.dto.BookingReservedEvent;

public interface PaymentEventPublisher extends DomainEventPublisher<BookingReservedEvent> {
}
