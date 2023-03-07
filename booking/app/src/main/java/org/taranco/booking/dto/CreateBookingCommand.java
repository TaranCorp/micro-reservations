package org.taranco.booking.dto;

import org.taranco.CustomerId;
import org.taranco.HotelId;
import org.taranco.booking.Room;
import org.taranco.vo.DateRange;

import java.util.HashSet;
import java.util.Set;

public class CreateBookingCommand {
    private final DateRange bookingPeriod;
    private final CustomerId customerId;
    private final HotelId hotelId;
    private final Set<Room> rooms = new HashSet<>();

    public CreateBookingCommand(DateRange bookingPeriod, CustomerId customerId, HotelId hotelId, Set<Room> rooms) {
        this.bookingPeriod = bookingPeriod;
        this.customerId = customerId;
        this.hotelId = hotelId;
        this.rooms.addAll(rooms);
    }

    public DateRange bookingPeriod() {
        return bookingPeriod;
    }

    public CustomerId customerId() {
        return customerId;
    }

    public HotelId hotelId() {
        return hotelId;
    }

    public Set<Room> rooms() {
        return rooms;
    }
}
