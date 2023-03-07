package org.taranco.booking.dto;

import org.taranco.BookingId;

public class CreateBookingResponse {
    private BookingId bookingId;

    public CreateBookingResponse() {
    }

    public CreateBookingResponse(BookingId bookingId) {
        this.bookingId = bookingId;
    }

    public BookingId bookingId() {
        return bookingId;
    }
}
