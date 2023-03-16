package org.taranco.hotel.port.input;

import org.taranco.BookingId;
import org.taranco.CustomerId;
import org.taranco.HotelId;
import org.taranco.TimesheetId;

import java.util.Set;

public interface RoomBookingRequestListener {
    void bookRooms(Set<TimesheetId> timesheetIds, CustomerId ownerId, HotelId hotelId, BookingId bookingId);
}
