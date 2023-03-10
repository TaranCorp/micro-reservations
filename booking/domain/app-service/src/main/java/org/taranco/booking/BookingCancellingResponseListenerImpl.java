package org.taranco.booking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.taranco.NotFoundException;
import org.taranco.booking.dto.CancellationResponse;
import org.taranco.booking.port.input.BookingCancellingResponseListener;
import org.taranco.booking.port.output.BookingRepository;

public class BookingCancellingResponseListenerImpl implements BookingCancellingResponseListener {
    private static final Logger log = LoggerFactory.getLogger(BookingCancellingResponseListenerImpl.class);

    private final BookingRepository bookingRepository;

    BookingCancellingResponseListenerImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public void cancellationCompleted(CancellationResponse response) {
        final Booking cancelledBooking = bookingRepository.findById(response.bookingId().getId())
                .map(booking -> {
                    log.info("Rooms released for booking with id: {}", booking.getSnapshot().getBookingId().getId().toString());
                    return booking.cancel();
                })
                .orElseThrow(() -> new NotFoundException("Cannot find Booking with id: %s. It does not exists or was processed".formatted(response.bookingId().getId().toString())));

        bookingRepository.save(cancelledBooking);
    }
}
