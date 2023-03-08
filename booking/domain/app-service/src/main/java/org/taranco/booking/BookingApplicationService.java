package org.taranco.booking;

import org.taranco.BookingId;
import org.taranco.booking.dto.CreateBookingCommand;
import org.taranco.booking.dto.CreateBookingResponse;

public interface BookingApplicationService {
    CreateBookingResponse createBooking(CreateBookingCommand command);

    Booking trackBooking(BookingId bookingId);
}
