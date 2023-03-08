package org.taranco.booking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.taranco.BookingId;
import org.taranco.NotFoundException;
import org.taranco.booking.dto.BookingCreatedEvent;
import org.taranco.hotel.ReservationCreator;
import org.taranco.booking.dto.CreateBookingCommand;
import org.taranco.booking.dto.CreateBookingResponse;
import org.taranco.booking.dto.RoomHolder;

import java.util.UUID;
import java.util.stream.Collectors;

public class BookingApplicationServiceImpl implements BookingApplicationService {
    private static final Logger log = LoggerFactory.getLogger(BookingApplicationServiceImpl.class);

    private final BookingRepository bookingRepository;
    private final ReservationCreator reservationCreator;

    public BookingApplicationServiceImpl(BookingRepository bookingRepository,
                                         ReservationCreator reservationCreator) {
        this.bookingRepository = bookingRepository;
        this.reservationCreator = reservationCreator;
    }

    @Override
    public CreateBookingResponse createBooking(CreateBookingCommand command) {
        final Booking persistedBooking = bookingRepository.save(new Booking(
                new BookingId(UUID.randomUUID()), command.customerId(), command.bookingPeriod(), command.hotelId(), command.rooms().stream().map(roomHolder -> Room.create(roomHolder.roomId(), roomHolder.vacancies())).collect(Collectors.toSet()))
        );

        log.info("Booking with id: {} was created", persistedBooking.getSnapshot().bookingId().toString());

        final BookingSnapshot persistedBookingSnapshot = persistedBooking.getSnapshot();

        reservationCreator.publishReservationRequest(new BookingCreatedEvent(
                persistedBookingSnapshot.bookingId(),
                persistedBookingSnapshot.customerId(),
                persistedBookingSnapshot.hotelId(),
                persistedBookingSnapshot.bookingPeriod(),
                persistedBookingSnapshot.rooms().stream().map(room -> new RoomHolder(room.roomId(), room.vacancies())).collect(Collectors.toSet())
        ));

        return new CreateBookingResponse(persistedBookingSnapshot.bookingId());
    }

    @Override
    public Booking trackBooking(BookingId bookingId) {
        return bookingRepository.findById(bookingId.getId())
                .orElseThrow(() -> new NotFoundException("Cannot find booking with id: %s".formatted(bookingId.getId().toString())));
    }
}
