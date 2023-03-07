package org.taranco.booking.dto;

import org.taranco.BookingId;

public class CancellationResponse {
    private final BookingId bookingId;

    public CancellationResponse(BookingId bookingId) {
        this.bookingId = bookingId;
    }

    public BookingId bookingId() {
        return bookingId;
    }
}
