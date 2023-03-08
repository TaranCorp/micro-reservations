package org.taranco.booking.dto;

import org.taranco.BookingId;

import java.util.UUID;

public class CreateBookingResponse {
    private BookingId bookingId;

    public CreateBookingResponse() {
    }

    public CreateBookingResponse(BookingId bookingId) {
        this.bookingId = bookingId;
    }

    public UUID getBookingId() {
        return bookingId.getId();
    }
}
