package org.taranco.booking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.taranco.NotFoundException;
import org.taranco.booking.dto.BookingCancellingEvent;
import org.taranco.booking.dto.BookingPaidEvent;
import org.taranco.booking.dto.PaymentResponse;
import org.taranco.hotel.BookingAcceptator;
import org.taranco.hotel.BookingCancellator;
import org.taranco.booking.dto.RoomHolder;

import java.util.stream.Collectors;

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
        final Booking paidBooking = bookingRepository.findById(response.bookingId().getId()).map(booking -> {
                    booking.pay(response.paymentDate());
                    log.info("Paid for booking with id: {}", booking.getSnapshot().bookingId().getId().toString());
                    return booking;
                })
                .orElseThrow(() -> new NotFoundException("Cannot find Booking with id: %s. It does not exists or was processed".formatted(response.bookingId().getId().toString())));

        final BookingSnapshot paidBookingSnapshot = bookingRepository.save(paidBooking).getSnapshot();

        log.info("Publishing BookingPaidEvent with id: {}", paidBookingSnapshot.bookingId().getId().toString());
        bookingAcceptator.publishBookingApproveRequest(new BookingPaidEvent(
                paidBookingSnapshot.bookingId(),
                paidBookingSnapshot.hotelId(),
                paidBookingSnapshot.rooms().stream().map(room -> new RoomHolder(room.roomId(), room.vacancies())).collect(Collectors.toSet())
        ));
    }

    @Override
    public void paymentCancelled(PaymentResponse response) {
        final Booking cancellingBooking = bookingRepository.findById(response.bookingId().getId()).map(booking -> {
                    booking.initCancel();
                    log.error("Init cancellation of booking with id: {}", booking.getSnapshot().bookingId().getId().toString());
                    return booking;
                })
                .orElseThrow(() -> new NotFoundException("Cannot find Booking with id: %s. It does not exists or was processed".formatted(response.bookingId().getId().toString())));

        final BookingSnapshot cancellingBookingSnapshot = bookingRepository.save(cancellingBooking).getSnapshot();

        log.error("Publishing BookingCancellingEvent of booking with id: {}", cancellingBookingSnapshot.bookingId().getId().toString());
        bookingCancellator.publishCancellingBookingRequest(new BookingCancellingEvent(
                cancellingBookingSnapshot.bookingId(),
                cancellingBookingSnapshot.hotelId(),
                cancellingBookingSnapshot.rooms().stream().map(room -> new RoomHolder(room.roomId(), room.vacancies())).collect(Collectors.toSet())
        ));
    }
}
