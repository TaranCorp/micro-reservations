package org.taranco.hotel;

import org.taranco.DomainEventPublisher;
import org.taranco.booking.dto.BookingPaidEvent;

public interface BookingApproveEventPublisher extends DomainEventPublisher<BookingPaidEvent> {
}
