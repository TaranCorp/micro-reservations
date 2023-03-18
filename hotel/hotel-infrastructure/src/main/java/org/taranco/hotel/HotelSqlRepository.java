package org.taranco.hotel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.taranco.HotelId;
import org.taranco.RoomId;
import org.taranco.hotel.port.output.HotelRepository;

import java.util.Optional;

@Repository
class HotelSqlRepository implements HotelRepository {
    private final HotelJpaRepository hotelJpaRepository;
    private final RoomJpaRepository roomJpaRepository;

    HotelSqlRepository(HotelJpaRepository hotelJpaRepository, RoomJpaRepository roomJpaRepository) {
        this.hotelJpaRepository = hotelJpaRepository;
        this.roomJpaRepository = roomJpaRepository;
    }

    @Override
    public Hotel save(Hotel hotel) {
        roomJpaRepository.saveAll(hotel.getSnapshot().getRooms());
        return Hotel.restore(hotelJpaRepository.save(hotel.getSnapshot()));
    }

    @Override
    public Optional<Hotel> findById(HotelId hotelId) {
        return hotelJpaRepository.findById(hotelId)
                .map(Hotel::restore);
    }
}

interface HotelJpaRepository extends JpaRepository<HotelSnapshot, HotelId> {
}

interface RoomJpaRepository extends JpaRepository<RoomSnapshot, RoomId> {
}