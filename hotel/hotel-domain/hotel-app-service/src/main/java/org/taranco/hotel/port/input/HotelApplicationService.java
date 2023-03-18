package org.taranco.hotel.port.input;

import org.taranco.BookingId;
import org.taranco.CustomerId;
import org.taranco.HotelId;
import org.taranco.TimesheetId;
import org.taranco.hotel.dto.CreatedHotelResponse;
import org.taranco.hotel.dto.RoomCreateCommand;
import org.taranco.hotel.vo.ReservationResponse;
import org.taranco.hotel.vo.ReserveRoomCommand;

import java.util.Set;

public interface HotelApplicationService {
    CreatedHotelResponse initHotel(String name, Set<RoomCreateCommand> rooms);

    ReservationResponse reserveRooms(ReserveRoomCommand reserveRoomCommands, HotelId hotelId, BookingId bookingId);

    void bookRooms(Set<TimesheetId> timesheetIds, CustomerId ownerId, HotelId hotelId, BookingId bookingId);

    void releaseRooms(Set<TimesheetId> timesheetIds, CustomerId ownerId, HotelId hotelId, BookingId bookingId);
}
