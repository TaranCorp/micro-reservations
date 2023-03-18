package org.taranco.hotel;

import org.taranco.BookingId;
import org.taranco.CustomerId;
import org.taranco.HotelId;
import org.taranco.TimesheetId;
import org.taranco.hotel.port.input.HotelApplicationService;
import org.taranco.hotel.port.input.RoomCancellationRequestListener;

import java.util.Set;

public class RoomCancellationRequestListenerImpl implements RoomCancellationRequestListener {
    private final HotelApplicationService hotelApplicationService;

    public RoomCancellationRequestListenerImpl(HotelApplicationService hotelApplicationService) {
        this.hotelApplicationService = hotelApplicationService;
    }

    @Override
    public void releaseRooms(Set<TimesheetId> timesheetIds, CustomerId ownerId, HotelId hotelId, BookingId bookingId) {
        hotelApplicationService.releaseRooms(timesheetIds, ownerId, hotelId, bookingId);
    }
}
