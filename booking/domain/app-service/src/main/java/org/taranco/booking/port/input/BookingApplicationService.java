package org.taranco.booking.port.input;

import org.taranco.BookingId;
import org.taranco.booking.dto.BookingTracker;
import org.taranco.booking.dto.CreateBookingCommand;
import org.taranco.booking.dto.CreateBookingResponse;

public interface BookingApplicationService {
    CreateBookingResponse createBooking(CreateBookingCommand command);

    BookingTracker trackBooking(BookingId bookingId);
}
