package org.taranco.booking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.taranco.BookingId;
import org.taranco.CustomerId;
import org.taranco.DomainEventPublisher;
import org.taranco.HotelId;
import org.taranco.booking.dto.BookingCreatedEvent;
import org.taranco.booking.dto.CreateBookingCommand;
import org.taranco.booking.dto.CreateBookingResponse;
import org.taranco.hotel.ReservationCreator;
import org.taranco.vo.DateRange;

import java.util.Set;
import java.util.UUID;

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
                new BookingId(UUID.randomUUID()), command.customerId(), command.bookingPeriod(), command.hotelId(), command.rooms())
        );
        log.info("Booking with id: {} was created", persistedBooking.getSnapshot().bookingId().toString());

        final BookingSnapshot persistedBookingSnapshot = persistedBooking.getSnapshot();

        reservationCreator.publishReservationRequest(new BookingCreatedEvent(
                persistedBookingSnapshot.bookingId(),
                persistedBookingSnapshot.customerId(),
                persistedBookingSnapshot.hotelId(),
                persistedBookingSnapshot.bookingPeriod(),
                persistedBookingSnapshot.rooms()
        ));

        log.info("BookingCreatedEvent with id: {} was published", persistedBooking.getSnapshot().bookingId().id().toString());
        return new CreateBookingResponse(persistedBookingSnapshot.bookingId());
    }

    @Override
    public Booking trackBooking(BookingId bookingId) {
        return null;
    }
}
