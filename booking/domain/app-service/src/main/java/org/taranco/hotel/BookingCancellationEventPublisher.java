package org.taranco.hotel;

import org.taranco.DomainEventPublisher;
import org.taranco.booking.dto.BookingCancellingEvent;

public interface BookingCancellationEventPublisher extends DomainEventPublisher<BookingCancellingEvent> {
}
