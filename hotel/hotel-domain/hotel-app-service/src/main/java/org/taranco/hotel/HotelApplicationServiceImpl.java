package org.taranco.hotel;

import org.taranco.BookingId;
import org.taranco.CustomerId;
import org.taranco.HotelId;
import org.taranco.NotFoundException;
import org.taranco.TimesheetId;
import org.taranco.hotel.dto.BookedRoomsResponse;
import org.taranco.hotel.dto.ReleasedRoomsResponse;
import org.taranco.hotel.dto.ReservedRoomsResponse;
import org.taranco.hotel.port.input.HotelApplicationService;
import org.taranco.hotel.port.output.BookedRoomsResponsePublisher;
import org.taranco.hotel.port.output.HotelRepository;
import org.taranco.hotel.port.output.ReleasedRoomsResponsePublisher;
import org.taranco.hotel.port.output.ReservedRoomsResponsePublisher;
import org.taranco.hotel.vo.ReservationResponse;
import org.taranco.hotel.vo.ReserveRoomCommand;

import java.time.Instant;
import java.util.Set;

class HotelApplicationServiceImpl implements HotelApplicationService {
    private final HotelRepository hotelRepository;
    private final ReservedRoomsResponsePublisher reservedRoomsResponsePublisher;
    private final ReleasedRoomsResponsePublisher releasedRoomsResponsePublisher;
    private final BookedRoomsResponsePublisher bookedRoomsResponsePublisher;

    public HotelApplicationServiceImpl(HotelRepository hotelRepository,
                                       ReservedRoomsResponsePublisher reservedRoomsResponsePublisher,
                                       ReleasedRoomsResponsePublisher releasedRoomsResponsePublisher,
                                       BookedRoomsResponsePublisher bookedRoomsResponsePublisher) {
        this.hotelRepository = hotelRepository;
        this.reservedRoomsResponsePublisher = reservedRoomsResponsePublisher;
        this.releasedRoomsResponsePublisher = releasedRoomsResponsePublisher;
        this.bookedRoomsResponsePublisher = bookedRoomsResponsePublisher;
    }

    @Override
    public Hotel initHotel(HotelId hotelId, String name, Set<Room> rooms) {
        return hotelRepository.save(
                Hotel.create(hotelId, name, rooms)
        );
    }

    @Override
    public ReservationResponse reserveRooms(ReserveRoomCommand reserveRoomCommand, HotelId hotelId, BookingId bookingId) {
        if (hotelId == null) {
            throw new IllegalArgumentException("Hotel id cannot be null");
        }
        return hotelRepository.findById(hotelId)
                .map(hotel -> {
                    ReservationResponse reservationResponse = hotel.reserveRooms(reserveRoomCommand);
                    hotelRepository.save(hotel);
                    reservedRoomsResponsePublisher.publish(new ReservedRoomsResponse(
                            Instant.now(),
                            reservationResponse.getBookingPeriod(),
                            reservationResponse.getHotelId(),
                            reservationResponse.getHotelName(),
                            reservationResponse.getTimesheetIds(),
                            reservationResponse.getTotalPrice(),
                            Instant.now(),
                            bookingId
                    ));
                    return reservationResponse;
                })
                .orElseThrow(() -> new NotFoundException("Cannot find hotel with id: " + hotelId.getId().toString()));
    }

    @Override
    public void bookRooms(Set<TimesheetId> timesheetIds, CustomerId ownerId, HotelId hotelId, BookingId bookingId) {
        if (hotelId == null) {
            throw new IllegalArgumentException("Hotel id cannot be null");
        }
        hotelRepository.findById(hotelId)
                .map(hotel -> {
                    Hotel hotelWithBookedRooms = hotel.bookRooms(timesheetIds, ownerId);
                    hotelRepository.save(hotel);
                    bookedRoomsResponsePublisher.publish(new BookedRoomsResponse(
                            Instant.now(),
                            Instant.now(),
                            bookingId
                    ));
                    return hotelWithBookedRooms;
                })
                .orElseThrow(() -> new NotFoundException("Cannot find hotel with id: " + hotelId.getId().toString()));
    }

    @Override
    public void releaseRooms(Set<TimesheetId> timesheetIds, CustomerId ownerId, HotelId hotelId, BookingId bookingId) {
        if (hotelId == null) {
            throw new IllegalArgumentException("Hotel id cannot be null");
        }
        hotelRepository.findById(hotelId)
                .map(hotel -> {
                    Hotel hotelWithBookedRooms = hotel.releaseRooms(timesheetIds, ownerId);
                    hotelRepository.save(hotel);
                    releasedRoomsResponsePublisher.publish(new ReleasedRoomsResponse(
                            Instant.now(),
                            Instant.now(),
                            bookingId
                    ));
                    return hotelWithBookedRooms;
                })
                .orElseThrow(() -> new NotFoundException("Cannot find hotel with id: " + hotelId.getId().toString()));
    }
}
