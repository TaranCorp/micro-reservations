package org.taranco.booking.dto;

import org.taranco.BookingId;

import java.time.Instant;

public class ApprovalResponse {
    private final BookingId bookingId;
    private final Instant bookingDate;

    public ApprovalResponse(BookingId bookingId, Instant bookingDate) {
        this.bookingId = bookingId;
        this.bookingDate = bookingDate;
    }

    public BookingId bookingId() {
        return bookingId;
    }

    public Instant bookingDate() {
        return bookingDate;
    }
}
