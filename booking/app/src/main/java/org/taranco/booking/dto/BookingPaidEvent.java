package org.taranco.booking.dto;

import org.taranco.BookingId;
import org.taranco.DomainEvent;
import org.taranco.HotelId;
import org.taranco.booking.Room;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

public class BookingPaidEvent implements DomainEvent {
    private final BookingId bookingId;
    private final Instant occurredOn;
    private final HotelId hotelId;
    private final Set<Room> rooms = new HashSet<>();

    public BookingPaidEvent(BookingId bookingId, HotelId hotelId, Set<Room> rooms) {
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

    public Set<Room> rooms() {
        return rooms;
    }
}
