package org.taranco.hotel.port.input;

import org.taranco.BookingId;
import org.taranco.HotelId;
import org.taranco.hotel.vo.ReserveRoomCommand;

public interface RoomReservationRequestListener {
    void reserveRooms(ReserveRoomCommand reserveRoomCommands, HotelId hotelId, BookingId bookingId);
}
