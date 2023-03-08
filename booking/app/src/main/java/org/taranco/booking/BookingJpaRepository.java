package org.taranco.booking;

import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
class BookingJpaRepository implements BookingRepository {

    @Override
    public Booking save(Booking booking) {
        return booking;
    }

    @Override
    public Optional<Booking> findById(UUID id) {
        return Optional.empty();
    }
}
