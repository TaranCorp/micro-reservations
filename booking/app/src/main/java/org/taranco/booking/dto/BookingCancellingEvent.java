package org.taranco.booking.dto;

import org.taranco.BookingId;
import org.taranco.DomainEvent;
import org.taranco.HotelId;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

public class BookingCancellingEvent implements DomainEvent {
    private final BookingId bookingId;
    private final Instant occurredOn;
    private final HotelId hotelId;
    private final Set<RoomHolder> rooms = new HashSet<>();

    public BookingCancellingEvent(BookingId bookingId, HotelId hotelId, Set<RoomHolder> rooms) {
        this.bookingId = bookingId;
        this.occurredOn = Instant.now();
        this.hotelId = hotelId;
        this.rooms.addAll(rooms);
    }

    @Override
    public Instant occurredOn() {
        return occurredOn;
    }

    public BookingId bookingId() {
        return bookingId;
    }

    public HotelId hotelId() {
        return hotelId;
    }

    public Set<RoomHolder> rooms() {
        return rooms;
    }
}
