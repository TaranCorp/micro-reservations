package org.taranco.booking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.taranco.NotFoundException;
import org.taranco.booking.dto.BookingReservedEvent;
import org.taranco.booking.dto.ReservationResponse;
import org.taranco.booking.port.input.ReservationResponseListener;
import org.taranco.booking.port.output.BookingRepository;
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
        final Booking reservedBooking = bookingRepository.findById(response.getBookingId().getId())
                .map(booking -> {
                    log.info("Reserved rooms for booking with id: {}", booking.getSnapshot().getBookingId().getId().toString());
                    return booking.reserve(response.getHotelName(), response.getPrice());
                })
                .orElseThrow(() -> new NotFoundException("Cannot find Booking with id: %s. It does not exists or was processed".formatted(response.getBookingId().getId().toString())));

        final BookingSnapshot bookingSnapshot = bookingRepository.save(reservedBooking).getSnapshot();

        log.info("Publishing BookingReservedEvent with id: {}", bookingSnapshot.getBookingId().getId().toString());
        paymentCreator.publishPaymentRequest(new BookingReservedEvent(
                bookingSnapshot.getBookingId(), bookingSnapshot.getPrice(), bookingSnapshot.getCustomerId()
        ));
    }

    @Override
    public void reservationCancelled(ReservationResponse response) {
        final Booking cancelledBooking = bookingRepository.findById(response.getBookingId().getId())
                .map(booking -> {
                    log.error("Cancelled booking with id: {}", booking.getSnapshot().getBookingId().getId().toString());
                    return booking.cancel();
                })
                .orElseThrow(() -> new NotFoundException("Cannot find Booking with id: %s. It does not exists or was processed".formatted(response.getBookingId().getId().toString())));

        bookingRepository.save(cancelledBooking).getSnapshot();
    }
}
