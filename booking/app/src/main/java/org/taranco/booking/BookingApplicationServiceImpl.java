package org.taranco.booking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.taranco.BookingId;
import org.taranco.CustomerId;
import org.taranco.DomainEventPublisher;
import org.taranco.HotelId;
import org.taranco.booking.dto.BookingCreatedEvent;
import org.taranco.vo.DateRange;

import java.util.Set;
import java.util.UUID;

public class BookingApplicationServiceImpl implements BookingApplicationService {
    private static final Logger log = LoggerFactory.getLogger(BookingApplicationServiceImpl.class);

    private final BookingRepository bookingRepository;
    private final DomainEventPublisher publisher;

    public BookingApplicationServiceImpl(BookingRepository bookingRepository,
                                         DomainEventPublisher publisher) {
        this.bookingRepository = bookingRepository;
        this.publisher = publisher;
    }

    @Override
    public Booking createBooking(DateRange bookingPeriod, CustomerId customerId, HotelId hotelId, Set<Room> rooms) {
        final Booking persistedBooking = bookingRepository.save(new Booking(
                        new BookingId(UUID.randomUUID()), customerId, bookingPeriod, hotelId, rooms)
        );
        log.info("Booking with id: {} was created", persistedBooking.getSnapshot().bookingId().toString());

        BookingSnapshot persistedBookingSnapshot = persistedBooking.getSnapshot();
        publisher.publish(new BookingCreatedEvent(
                persistedBookingSnapshot.bookingId(),
                persistedBookingSnapshot.customerId(),
                persistedBookingSnapshot.hotelId(),
                persistedBookingSnapshot.bookingPeriod(),
                persistedBookingSnapshot.rooms()
        ));

        log.info("BookingCreatedEvent with id: {} was published", persistedBooking.getSnapshot().bookingId().toString());
        return persistedBooking;
    }

    @Override
    public Booking trackBooking(BookingId bookingId) {
        return null;
    }
}
