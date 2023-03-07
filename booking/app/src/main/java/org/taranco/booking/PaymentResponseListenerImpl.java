package org.taranco.booking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.taranco.NotFoundException;
import org.taranco.booking.dto.BookingCancellingEvent;
import org.taranco.booking.dto.BookingPaidEvent;
import org.taranco.booking.dto.PaymentResponse;
import org.taranco.hotel.BookingAcceptator;
import org.taranco.hotel.BookingCancellator;

public class PaymentResponseListenerImpl implements PaymentResponseListener {
    private static final Logger log = LoggerFactory.getLogger(PaymentResponseListenerImpl.class);

    private final BookingRepository bookingRepository;
    private final BookingAcceptator bookingAcceptator;
    private final BookingCancellator bookingCancellator;

    PaymentResponseListenerImpl(BookingRepository bookingRepository, BookingAcceptator bookingAcceptator, BookingCancellator bookingCancellator) {
        this.bookingRepository = bookingRepository;
        this.bookingAcceptator = bookingAcceptator;
        this.bookingCancellator = bookingCancellator;
    }

    @Override
    public void paymentCompleted(PaymentResponse response) {
        final Booking paidBooking = bookingRepository.findById(response.bookingId().id()).map(booking -> {
                    booking.pay(response.paymentDate());
                    log.info("Paid for booking with id: {}", booking.getSnapshot().bookingId().id().toString());
                    return booking;
                })
                .orElseThrow(() -> new NotFoundException("Cannot find Booking with id: %s. It not exists or was processed".formatted(response.bookingId().id().toString())));

        final BookingSnapshot paidBookingSnapshot = bookingRepository.save(paidBooking).getSnapshot();

        log.info("Publishing BookingPaidEvent with id: {}", paidBookingSnapshot.bookingId().id().toString());
        bookingAcceptator.publishBookingApproveEvent(new BookingPaidEvent(
                paidBookingSnapshot.bookingId(),
                paidBookingSnapshot.hotelId(),
                paidBookingSnapshot.rooms()
        ));
    }

    @Override
    public void paymentCancelled(PaymentResponse response) {
        final Booking cancellingBooking = bookingRepository.findById(response.bookingId().id()).map(booking -> {
                    booking.initCancel();
                    log.error("Init cancellation of booking with id: {}", booking.getSnapshot().bookingId().id().toString());
                    return booking;
                })
                .orElseThrow(() -> new NotFoundException("Cannot find Booking with id: %s. It not exists or was processed".formatted(response.bookingId().id().toString())));

        final BookingSnapshot cancellingBookingSnapshot = bookingRepository.save(cancellingBooking).getSnapshot();

        log.error("Publishing BookingCancellingEvent of booking with id: {}", cancellingBookingSnapshot.bookingId().id().toString());
        bookingCancellator.publishCancellingBookingEvent(new BookingCancellingEvent(
                cancellingBookingSnapshot.bookingId(),
                cancellingBookingSnapshot.hotelId(),
                cancellingBookingSnapshot.rooms()
        ));
    }
}
