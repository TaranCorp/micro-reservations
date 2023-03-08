package org.taranco.booking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.taranco.NotFoundException;
import org.taranco.booking.dto.CancellationResponse;

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
                    booking.cancel();
                    log.info("Rooms released for booking with id: {}", booking.getSnapshot().bookingId().getId().toString());
                    return booking;
                })
                .orElseThrow(() -> new NotFoundException("Cannot find Booking with id: %s. It does not exists or was processed".formatted(response.bookingId().getId().toString())));

        bookingRepository.save(cancelledBooking);
    }
}
