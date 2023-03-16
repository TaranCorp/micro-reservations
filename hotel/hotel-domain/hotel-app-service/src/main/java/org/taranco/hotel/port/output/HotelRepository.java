package org.taranco.hotel.port.output;

import org.taranco.HotelId;
import org.taranco.hotel.Hotel;

import java.util.Optional;

public interface HotelRepository {
    Hotel save(Hotel hotel);

    Optional<Hotel> findById(HotelId hotelId);
}
