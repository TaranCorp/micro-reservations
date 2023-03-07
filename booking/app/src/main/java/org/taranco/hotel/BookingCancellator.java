package org.taranco.hotel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.taranco.booking.dto.BookingCancellingEvent;

public class BookingCancellator {
    private static final Logger log = LoggerFactory.getLogger(BookingCancellator.class);

    private final BookingCancellationEventPublisher bookingCancellationPublisher;

    BookingCancellator(BookingCancellationEventPublisher bookingCancellationPublisher) {
        this.bookingCancellationPublisher = bookingCancellationPublisher;
    }

    public void publishCancellingBookingRequest(BookingCancellingEvent event) {
        if (event == null) {
            throw new IllegalArgumentException("BookingCancellingEvent cannot be null");
        }

        log.info("Started cancellation process of booking with id: {}", event.bookingId().id().toString());
        bookingCancellationPublisher.publish(event);
    }
}
