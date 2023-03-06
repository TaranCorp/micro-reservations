package org.taranco.hotel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.taranco.booking.dto.BookingCreatedEvent;

class ReservationCreator {
    private static final Logger log = LoggerFactory.getLogger(ReservationCreator.class);

    private final ReservationEventPublisher reservationEventPublisher;

    ReservationCreator(ReservationEventPublisher reservationEventPublisher) {
        this.reservationEventPublisher = reservationEventPublisher;
    }

    void publishReservationRequest(BookingCreatedEvent bookingCreatedEvent) {
        if (bookingCreatedEvent == null) {
            throw new IllegalArgumentException("Cannot process null booking event");
        }

        log.info("Start of reservation for booking id: {}", bookingCreatedEvent.bookingId().toString());
        reservationEventPublisher.publish(bookingCreatedEvent);
    }
}
