package org.taranco.booking;

import org.taranco.BookingId;
import org.taranco.CustomerId;
import org.taranco.HotelId;
import org.taranco.vo.DateRange;

import java.util.Set;

public interface BookingApplicationService {
    Booking createBooking(DateRange bookingPeriod, CustomerId customerId, HotelId hotelId, Set<Room> rooms);

    Booking trackBooking(BookingId bookingId);
}
