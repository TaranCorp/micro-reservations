package org.taranco.booking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.taranco.NotFoundException;
import org.taranco.booking.dto.ApprovalResponse;
import org.taranco.booking.port.input.RoomApprovalListener;
import org.taranco.booking.port.output.BookingRepository;

public class RoomApprovalListenerImpl implements RoomApprovalListener {
    private static final Logger log = LoggerFactory.getLogger(RoomApprovalListenerImpl.class);

    private final BookingRepository bookingRepository;

    RoomApprovalListenerImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public void approvalCompleted(ApprovalResponse response) {
        final Booking approvedBooking = bookingRepository.findById(response.bookingId().getId())
                .map(booking -> {
                    log.info("Approved rooms for booking with id: {}", booking.getSnapshot().getBookingId().getId().toString());
                    return booking.book(response.bookingDate());
                })
                .orElseThrow(() -> new NotFoundException("Cannot find Booking with id: %s. It does not exists or was processed".formatted(response.bookingId().getId().toString())));

        bookingRepository.save(approvedBooking);
    }
}
