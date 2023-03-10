package org.taranco.booking.port.output;

import org.taranco.DomainEventPublisher;
import org.taranco.booking.dto.BookingPaidEvent;

public interface BookingApproveEventPublisher extends DomainEventPublisher<BookingPaidEvent> {
}
