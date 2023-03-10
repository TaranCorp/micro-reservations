package org.taranco.booking;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.taranco.BookingId;
import org.taranco.CustomerId;
import org.taranco.HotelId;
import org.taranco.RoomId;
import org.taranco.booking.dto.BookingTracker;
import org.taranco.booking.dto.CreateBookingCommand;
import org.taranco.booking.dto.CreateBookingResponse;
import org.taranco.booking.dto.RoomHolder;
import org.taranco.vo.DateRange;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.UUID;

@RequestMapping("/api/booking")
@RestController
class BookingApi {

    private final BookingApplicationService bookingApplicationService;

    BookingApi(BookingApplicationService bookingApplicationService) {
        this.bookingApplicationService = bookingApplicationService;
    }

    @PostMapping
    CreateBookingResponse createBooking() {
        return bookingApplicationService.createBooking(
                new CreateBookingCommand(
                        DateRange.createFromNowTo(Instant.now().plus(1, ChronoUnit.DAYS)),
                        new CustomerId(UUID.randomUUID()),
                        new HotelId(UUID.randomUUID()),
                        Set.of(
                                new RoomHolder(new RoomId(UUID.randomUUID()), 2),
                                new RoomHolder(new RoomId(UUID.randomUUID()), 3),
                                new RoomHolder(new RoomId(UUID.randomUUID()), 4)
                        )
                )
        );
    }

    @GetMapping
    BookingTracker trackBooking(@RequestParam String bookingId) {
        return bookingApplicationService.trackBooking(new BookingId(UUID.fromString(bookingId)));
    }

    @ExceptionHandler(Exception.class)
    void handleNFE(Exception e, HttpServletResponse response) throws IOException {
        response.addHeader("Content-Type", "application/json");
        response.setStatus(400);
        response.getOutputStream().println(e.getMessage());
        throw new RuntimeException(e);
    }
}
