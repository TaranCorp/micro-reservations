package org.taranco.hotel;

import org.taranco.BookingId;
import org.taranco.CustomerId;
import org.taranco.HotelId;
import org.taranco.TimesheetId;
import org.taranco.hotel.port.input.HotelApplicationService;
import org.taranco.hotel.port.input.RoomBookingRequestListener;

import java.util.Set;

class RoomBookingRequestListenerImpl implements RoomBookingRequestListener {
    private final HotelApplicationService hotelApplicationService;

    RoomBookingRequestListenerImpl(HotelApplicationService hotelApplicationService) {
        this.hotelApplicationService = hotelApplicationService;
    }

    @Override
    public void bookRooms(Set<TimesheetId> timesheetIds, CustomerId ownerId, HotelId hotelId, BookingId bookingId) {
        hotelApplicationService.bookRooms(timesheetIds, ownerId, hotelId, bookingId);
    }
}
