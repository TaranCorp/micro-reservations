package org.taranco.booking.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.taranco.BookingId;
import org.taranco.CustomerId;
import org.taranco.DomainEvent;
import org.taranco.HotelId;
import org.taranco.vo.DateRange;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

public class BookingCreatedEvent implements DomainEvent {
    private final Instant occurredOn;
    private final BookingId bookingId;
    private final CustomerId customerId;
    private final HotelId hotelId;
    private final Set<RoomHolder> rooms = new HashSet<>();
    private final DateRange bookingPeriod;

    @JsonCreator
    public BookingCreatedEvent(
            @JsonProperty("bookingId") BookingId bookingId,
            @JsonProperty("customerId") CustomerId customerId,
            @JsonProperty("hotelId") HotelId hotelId,
            @JsonProperty("bookingPeriod") DateRange bookingPeriod,
            @JsonProperty("rooms") Set<RoomHolder> rooms
    ) {
        this.bookingId = bookingId;
        this.hotelId = hotelId;
        this.bookingPeriod = bookingPeriod;
        this.customerId = customerId;
        this.occurredOn = Instant.now();
        this.rooms.addAll(rooms);
    }

    @Override
    public Instant getOccurredOn() {
        return occurredOn;
    }

    public BookingId getBookingId() {
        return bookingId;
    }

    public CustomerId getCustomerId() {
        return customerId;
    }

    public HotelId getHotelId() {
        return hotelId;
    }

    public Set<RoomHolder> getRooms() {
        return rooms;
    }

    public DateRange getBookingPeriod() {
        return bookingPeriod;
    }
}
