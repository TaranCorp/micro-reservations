package org.taranco.booking.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.taranco.CustomerId;
import org.taranco.HotelId;
import org.taranco.vo.DateRange;

import java.util.HashSet;
import java.util.Set;

public class CreateBookingCommand {
    private final DateRange bookingPeriod;
    private final CustomerId customerId;
    private final HotelId hotelId;
    private final Set<RoomHolder> rooms = new HashSet<>();

    @JsonCreator
    public CreateBookingCommand(
            @JsonProperty("bookingPeriod") DateRange bookingPeriod,
            @JsonProperty("customerId") CustomerId customerId,
            @JsonProperty("hotelId") HotelId hotelId,
            @JsonProperty("rooms") Set<RoomHolder> rooms
    ) {
        this.bookingPeriod = bookingPeriod;
        this.customerId = customerId;
        this.hotelId = hotelId;
        this.rooms.addAll(rooms);
    }

    public DateRange getBookingPeriod() {
        return bookingPeriod;
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
}
