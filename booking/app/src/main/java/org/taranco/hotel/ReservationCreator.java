package org.taranco.hotel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.taranco.booking.vo.BookingEvent;

class ReservationCreator {
    private static final Logger log = LoggerFactory.getLogger(ReservationCreator.class);

    private final ReservationEventPublisher reservationEventPublisher;

    ReservationCreator(ReservationEventPublisher reservationEventPublisher) {
        this.reservationEventPublisher = reservationEventPublisher;
    }

    void publishReservationRequest(BookingEvent bookingEvent) {
        if (bookingEvent == null) {
            throw new IllegalArgumentException("Cannot process null booking event");
        }

        if (bookingEvent.state() == BookingEvent.State.CREATED) {
            log.info("Start of reservation for booking id: {}", bookingEvent.bookingId().toString());
            reserveRooms(bookingEvent);
        }
    }

    private void reserveRooms(BookingEvent bookingEvent) {
        reservationEventPublisher.publish(bookingEvent);
    }
}
