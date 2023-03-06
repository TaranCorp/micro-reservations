package org.taranco.hotel;

import org.taranco.booking.dto.BookingPaidEvent;

class BookingAcceptator {
    void publishBookingApproveEvent(BookingPaidEvent event) {
        if (event == null) {
            throw new IllegalArgumentException("BookingPaidEvent cannot be null");
        }

    }
}
