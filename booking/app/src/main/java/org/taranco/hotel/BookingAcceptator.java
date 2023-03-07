package org.taranco.hotel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.taranco.booking.dto.BookingPaidEvent;

public class BookingAcceptator {
    private static final Logger log = LoggerFactory.getLogger(BookingAcceptator.class);

    private final BookingApproveEventPublisher bookingApproveEventPublisher;

    BookingAcceptator(BookingApproveEventPublisher bookingApproveEventPublisher) {
        this.bookingApproveEventPublisher = bookingApproveEventPublisher;
    }

    public void publishBookingApproveRequest(BookingPaidEvent event) {
        if (event == null) {
            throw new IllegalArgumentException("BookingPaidEvent cannot be null");
        }

        log.info("Start of approving of booking with id: {}", event.bookingId().id().toString());
        bookingApproveEventPublisher.publish(event);
    }
}
