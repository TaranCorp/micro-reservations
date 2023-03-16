package org.taranco.hotel.dto;

import org.taranco.BookingId;
import org.taranco.DomainEvent;

import java.time.Instant;

public class ReleasedRoomsResponse implements DomainEvent {
    private final Instant occurredOn;
    private final Instant bookingDate;
    private final BookingId bookingId;

    public ReleasedRoomsResponse(Instant occurredOn, Instant bookingDate, BookingId bookingId) {
        this.occurredOn = occurredOn;
        this.bookingDate = bookingDate;
        this.bookingId = bookingId;
    }

    public Instant getBookingDate() {
        return bookingDate;
    }

    public BookingId getBookingId() {
        return bookingId;
    }

    @Override
    public Instant getOccurredOn() {
        return null;
    }
}
