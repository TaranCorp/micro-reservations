package org.taranco.hotel;

import org.taranco.BookingId;
import org.taranco.HotelId;
import org.taranco.hotel.port.input.HotelApplicationService;
import org.taranco.hotel.port.input.RoomReservationRequestListener;
import org.taranco.hotel.vo.ReserveRoomCommand;

class RoomReservationRequestListenerImpl implements RoomReservationRequestListener {
    private final HotelApplicationService hotelApplicationService;

    RoomReservationRequestListenerImpl(HotelApplicationService hotelApplicationService) {
        this.hotelApplicationService = hotelApplicationService;
    }

    @Override
    public void reserveRooms(ReserveRoomCommand reserveRoomCommands, HotelId hotelId, BookingId bookingId) {
        hotelApplicationService.reserveRooms(reserveRoomCommands, hotelId, bookingId);
    }
}
