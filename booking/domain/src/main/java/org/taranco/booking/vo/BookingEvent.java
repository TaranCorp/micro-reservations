package org.taranco.booking.vo;

import org.taranco.BookingId;
import org.taranco.DomainEvent;
import org.taranco.HotelId;
import org.taranco.booking.Room;
import org.taranco.vo.DateRange;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

public class BookingEvent implements DomainEvent {

    public enum State {
        CREATED, SENT, UPDATED
    }

    private final State state;
    private final Instant occurredOn;
    private final BookingId bookingId;
    private final HotelId hotelId;
    private final Set<Room> rooms = new HashSet<>();
    private final DateRange bookingPeriod;

    public BookingEvent(State state, BookingId bookingId, HotelId hotelId, DateRange bookingPeriod, Set<Room> rooms) {
        this.bookingId = bookingId;
        this.hotelId = hotelId;
        this.bookingPeriod = bookingPeriod;
        this.occurredOn = Instant.now();
        this.state = state;
        this.rooms.addAll(rooms);
    }

    public BookingEvent(State state, BookingEvent bookingEvent) {
        this(state, bookingEvent.bookingId, bookingEvent.hotelId, bookingEvent.bookingPeriod, bookingEvent.rooms);
    }

    @Override
    public Instant getOccurredOn() {
        return occurredOn;
    }

    public State state() {
        return state;
    }

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

    public DateRange bookingPeriod() {
        return bookingPeriod;
    }
}
