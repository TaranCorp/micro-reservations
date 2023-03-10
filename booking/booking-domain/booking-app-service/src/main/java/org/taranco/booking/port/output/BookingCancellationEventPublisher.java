package org.taranco.booking.port.output;

import org.taranco.DomainEventPublisher;
import org.taranco.booking.dto.BookingCancellingEvent;

public interface BookingCancellationEventPublisher extends DomainEventPublisher<BookingCancellingEvent> {
}
