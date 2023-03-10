package org.taranco.booking.port.output;

import org.taranco.booking.Booking;

import java.util.Optional;
import java.util.UUID;

public interface BookingRepository {
    Booking save(Booking booking);

    Optional<Booking> findById(UUID id);
}
