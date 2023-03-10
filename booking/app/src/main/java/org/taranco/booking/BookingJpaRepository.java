package org.taranco.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.taranco.BookingId;
import org.taranco.RoomId;

import java.util.Optional;
import java.util.UUID;

@Repository
class BookingJpaRepository implements BookingRepository {

    private final BookingRepositoryInterface repository;
    private final RoomRepositoryInterface roomRepository;

    BookingJpaRepository(BookingRepositoryInterface repository, RoomRepositoryInterface roomRepository) {
        this.repository = repository;
        this.roomRepository = roomRepository;
    }

    @Override
    public Booking save(Booking booking) {
        booking.getSnapshot().getRooms().forEach(roomRepository::save);
        return Booking.restore(repository.save(booking.getSnapshot()));
    }

    @Override
    public Optional<Booking> findById(UUID id) {
        return repository.findById(new BookingId(id))
                .map(Booking::restore);
    }
}

interface BookingRepositoryInterface extends JpaRepository<BookingSnapshot, BookingId> {}

interface RoomRepositoryInterface extends JpaRepository<RoomSnapshot, RoomId> {}
