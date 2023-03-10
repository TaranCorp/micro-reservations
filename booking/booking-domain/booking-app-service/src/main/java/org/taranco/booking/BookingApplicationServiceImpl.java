package org.taranco.booking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.taranco.BookingId;
import org.taranco.NotFoundException;
import org.taranco.booking.dto.BookingCreatedEvent;
import org.taranco.booking.dto.BookingTracker;
import org.taranco.booking.dto.RoomTracker;
import org.taranco.booking.port.input.BookingApplicationService;
import org.taranco.booking.port.output.BookingRepository;
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
        Booking booking = new Booking(
                new BookingId(UUID.randomUUID()),
                command.getCustomerId(),
                command.getBookingPeriod(),
                command.getHotelId()
        );
        booking.addRooms(command.getRooms().stream().map(roomHolder -> Room.create(roomHolder.getRoomId(), roomHolder.getVacancies())).collect(Collectors.toSet()));

        final Booking persistedBooking = bookingRepository.save(booking);

        log.info("Booking with id: {} was created", persistedBooking.getSnapshot().getBookingId().toString());

        final BookingSnapshot persistedBookingSnapshot = persistedBooking.getSnapshot();

        reservationCreator.publishReservationRequest(new BookingCreatedEvent(
                persistedBookingSnapshot.getBookingId(),
                persistedBookingSnapshot.getCustomerId(),
                persistedBookingSnapshot.getHotelId(),
                persistedBookingSnapshot.getBookingPeriod(),
                persistedBookingSnapshot.getRooms().stream().map(room -> new RoomHolder(room.getRoomId(), room.getVacancies())).collect(Collectors.toSet())
        ));

        return new CreateBookingResponse(persistedBookingSnapshot.getBookingId());
    }

    @Override
    public BookingTracker trackBooking(BookingId bookingId) {
        return bookingRepository.findById(bookingId.getId())
                .map(booking -> {
                    BookingSnapshot snapshot = booking.getSnapshot();
                    return new BookingTracker(
                            snapshot.getBookingId(),
                            snapshot.getCustomerId(),
                            snapshot.getHotelId(),
                            snapshot.getHotelName(),
                            snapshot.getRooms().stream().map(r -> new RoomTracker(r.getRoomId(), r.getVacancies())).collect(Collectors.toSet()),
                            snapshot.getBookingPeriod(),
                            snapshot.getBookingDate(),
                            snapshot.getPaymentDate(),
                            snapshot.getPrice(),
                            snapshot.getStatus().name()
                    );
                })
                .orElseThrow(() -> new NotFoundException("Cannot find booking with id: %s".formatted(bookingId.getId().toString())));
    }
}
