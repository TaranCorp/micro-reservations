package org.taranco.booking.dto;

import org.taranco.BookingId;
import org.taranco.CustomerId;
import org.taranco.DomainEvent;
import org.taranco.HotelId;
import org.taranco.booking.Room;
import org.taranco.vo.DateRange;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

public class BookingCreatedEvent implements DomainEvent {
    private final Instant occurredOn;
    private final BookingId bookingId;
    private final CustomerId customerId;
    private final HotelId hotelId;
    private final Set<Room> rooms = new HashSet<>();
    private final DateRange bookingPeriod;

    public BookingCreatedEvent(BookingId bookingId, CustomerId customerId, HotelId hotelId, DateRange bookingPeriod, Set<Room> rooms) {
        this.bookingId = bookingId;
        this.hotelId = hotelId;
        this.bookingPeriod = bookingPeriod;
        this.customerId = customerId;
        this.occurredOn = Instant.now();
        this.rooms.addAll(rooms);
    }

    @Override
    public Instant occurredOn() {
        return occurredOn;
    }

    public CustomerId customerId() {
        return customerId;
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

    public DateRange bookingPeriod() {
        return bookingPeriod;
    }
}
