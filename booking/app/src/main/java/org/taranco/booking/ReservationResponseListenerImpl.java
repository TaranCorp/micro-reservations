package org.taranco.booking;

import org.taranco.DomainEventPublisher;
import org.taranco.NotFoundException;
import org.taranco.booking.dto.BookingReservedEvent;
import org.taranco.booking.dto.ReservationResponse;

public class ReservationResponseListenerImpl implements ReservationResponseListener {

    private final BookingRepository bookingRepository;
    private final DomainEventPublisher publisher;

    public ReservationResponseListenerImpl(BookingRepository bookingRepository, DomainEventPublisher publisher) {
        this.bookingRepository = bookingRepository;
        this.publisher = publisher;
    }

    @Override
    public void reservationCompleted(ReservationResponse response) {
        final Booking reservedBooking = bookingRepository.findById(response.bookingId().id())
                .map(booking -> {
                    booking.reserve(response.hotelName(), response.price());
                    return booking;
                })
                .orElseThrow(() -> new NotFoundException("Cannot find Booking with id: %s. It not exists or was processed".formatted(response.bookingId().id())));

        final BookingSnapshot bookingSnapshot = bookingRepository.save(reservedBooking).getSnapshot();

        publisher.publish(new BookingReservedEvent(
                bookingSnapshot.bookingId(), bookingSnapshot.price(), bookingSnapshot.customerId()
        ));
    }

    @Override
    public void reservationCancelled(ReservationResponse response) {

    }
}
