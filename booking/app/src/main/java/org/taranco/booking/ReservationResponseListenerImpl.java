package org.taranco.booking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.taranco.NotFoundException;
import org.taranco.booking.dto.BookingReservedEvent;
import org.taranco.booking.dto.ReservationResponse;
import org.taranco.payment.PaymentCreator;

public class ReservationResponseListenerImpl implements ReservationResponseListener {
    private static final Logger log = LoggerFactory.getLogger(ReservationResponseListenerImpl.class);

    private final BookingRepository bookingRepository;
    private final PaymentCreator paymentCreator;

    public ReservationResponseListenerImpl(BookingRepository bookingRepository, PaymentCreator paymentCreator) {
        this.bookingRepository = bookingRepository;
        this.paymentCreator = paymentCreator;
    }

    @Override
    public void reservationCompleted(ReservationResponse response) {
        final Booking reservedBooking = bookingRepository.findById(response.bookingId().id())
                .map(booking -> {
                    booking.reserve(response.hotelName(), response.price());
                    log.info("Reserved rooms for booking with id: {}", booking.getSnapshot().bookingId().id().toString());
                    return booking;
                })
                .orElseThrow(() -> new NotFoundException("Cannot find Booking with id: %s. It not exists or was processed".formatted(response.bookingId().id().toString())));

        final BookingSnapshot bookingSnapshot = bookingRepository.save(reservedBooking).getSnapshot();

        log.info("Publishing BookingReservedEvent with id: {}", bookingSnapshot.bookingId().id().toString());
        paymentCreator.publishPaymentRequest(new BookingReservedEvent(
                bookingSnapshot.bookingId(), bookingSnapshot.price(), bookingSnapshot.customerId()
        ));
    }

    @Override
    public void reservationCancelled(ReservationResponse response) {
        final Booking cancelledBooking = bookingRepository.findById(response.bookingId().id())
                .map(booking -> {
                    booking.cancel();
                    log.error("Cancelled booking with id: {}", booking.getSnapshot().bookingId().id().toString());
                    return booking;
                })
                .orElseThrow(() -> new NotFoundException("Cannot find Booking with id: %s. It not exists or was processed".formatted(response.bookingId().id().toString())));

        bookingRepository.save(cancelledBooking).getSnapshot();
    }
}
