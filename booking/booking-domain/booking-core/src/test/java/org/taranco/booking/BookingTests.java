package org.taranco.booking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.taranco.BookingId;
import org.taranco.CustomerId;
import org.taranco.HotelId;
import org.taranco.vo.DateRange;
import org.taranco.vo.Money;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BookingTests {

    private BookingId bookingId;
    private CustomerId customerId;
    private HotelId hotelId;
    private DateRange dateRange;

    private Booking initializedBooking;
    private Booking reservedBooking;
    private Booking paidBooking;
    private Booking cancellingBooking;

    @BeforeEach
    void setup() {
        bookingId = new BookingId(UUID.randomUUID());
        customerId = new CustomerId(UUID.randomUUID());
        hotelId = new HotelId(UUID.randomUUID());
        dateRange = DateRange.createFromNowTo(Instant.now().plusSeconds(1));

        initializedBooking = new Booking(
                bookingId,
                customerId,
                dateRange,
                hotelId
        );

        reservedBooking = Booking.restore(new BookingSnapshot(
                bookingId,
                customerId,
                hotelId,
                "hotelName",
                Set.of(),
                DateRange.createFromNowTo(Instant.now().plusSeconds(1)),
                null,
                null,
                new Money(BigDecimal.ONE),
                BookingStatus.RESERVED
        ));

        paidBooking = Booking.restore(new BookingSnapshot(
                bookingId,
                customerId,
                hotelId,
                "hotelName",
                Set.of(),
                DateRange.createFromNowTo(Instant.now().plusSeconds(1)),
                null,
                null,
                new Money(BigDecimal.ONE),
                BookingStatus.PAID
        ));

        cancellingBooking = Booking.restore(new BookingSnapshot(
                bookingId,
                customerId,
                hotelId,
                "hotelName",
                Set.of(),
                DateRange.createFromNowTo(Instant.now().plusSeconds(1)),
                null,
                null,
                new Money(BigDecimal.ONE),
                BookingStatus.CANCELLING
        ));
    }

    @Test
    void whenBookingIsNotCreated_shouldCreateObjectAndInitCorrectly() {
        final BookingSnapshot bookingSnapshot = initializedBooking.getSnapshot();

        assertAll(
                () -> assertNotNull(bookingSnapshot),
                () -> assertEquals(BookingStatus.PENDING, bookingSnapshot.getStatus()),
                () -> assertEquals(bookingId, bookingSnapshot.getBookingId()),
                () -> assertEquals(hotelId, bookingSnapshot.getHotelId()),
                () -> assertEquals(customerId, bookingSnapshot.getCustomerId()),
                () -> assertEquals(dateRange, bookingSnapshot.getBookingPeriod())
        );
    }

    @Test
    void whenBookingIsInitialized_shouldReserveCorrectly() {
        String hotelName = "HotelName";
        Money price = new Money(BigDecimal.TEN);

        final Booking reservedBooking = initializedBooking.reserve(hotelName, price);

        assertNotNull(reservedBooking);

        final BookingSnapshot bookingSnapshot = reservedBooking.getSnapshot();

        assertAll(
                () -> assertEquals(BookingStatus.RESERVED, bookingSnapshot.getStatus()),
                () -> assertEquals(hotelName, bookingSnapshot.getHotelName()),
                () -> assertEquals(price, bookingSnapshot.getPrice())
        );
    }

    @Test
    void whenBookingIsReserved_shouldPayCorrectly() {
        Instant paymentDate = Instant.now();

        final Booking paidBooking = reservedBooking.pay(paymentDate);

        assertNotNull(paidBooking);

        final BookingSnapshot bookingSnapshot = paidBooking.getSnapshot();

        assertAll(
                () -> assertEquals(BookingStatus.PAID, bookingSnapshot.getStatus()),
                () -> assertEquals(paymentDate, bookingSnapshot.getPaymentDate())
        );
    }

    @Test
    void whenBookingIsPaid_shouldBookCorrectly() {
        Instant bookingDate = Instant.now();

        final Booking bookedBooking = paidBooking.book(bookingDate);

        assertNotNull(bookedBooking);

        final BookingSnapshot bookingSnapshot = bookedBooking.getSnapshot();

        assertAll(
                () -> assertEquals(BookingStatus.APPROVED, bookingSnapshot.getStatus()),
                () -> assertEquals(bookingDate, bookingSnapshot.getBookingDate())
        );
    }

    @Test
    void whenBookingWasNotPaidCorrect_shouldInitCancellation() {
        final Booking cancellingBooking = reservedBooking.initCancel();

        assertNotNull(cancellingBooking);
        assertEquals(BookingStatus.CANCELLING, cancellingBooking.getSnapshot().getStatus());
    }

    @Test
    void whenBookingWasInCancellingState_shouldCancelCorrectly() {
        final Booking cancelledBooking = cancellingBooking.cancel();

        assertNotNull(cancelledBooking);

        final BookingSnapshot bookingSnapshot = cancelledBooking.getSnapshot();

        assertAll(
                () -> assertEquals(BookingStatus.CANCELLED, bookingSnapshot.getStatus()),
                () -> assertTrue(bookingSnapshot.getRooms().isEmpty()),
                () -> assertNull(bookingSnapshot.getHotelId()),
                () -> assertNull(bookingSnapshot.getHotelName()),
                () -> assertNull(bookingSnapshot.getPaymentDate()),
                () -> assertNull(bookingSnapshot.getBookingDate()),
                () -> assertNull(bookingSnapshot.getPrice())
        );
    }

    @Test
    void whenBookingWasNotReservedCorrectly_shouldCancelCorrectly() {
        final Booking cancelledBooking = initializedBooking.cancel();

        assertNotNull(cancelledBooking);

        final BookingSnapshot bookingSnapshot = cancelledBooking.getSnapshot();

        assertAll(
                () -> assertEquals(BookingStatus.CANCELLED, bookingSnapshot.getStatus()),
                () -> assertTrue(bookingSnapshot.getRooms().isEmpty())
        );
    }
}
