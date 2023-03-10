package org.taranco.booking;

import org.taranco.BookingId;
import org.taranco.CustomerId;
import org.taranco.HotelId;
import org.taranco.vo.DateRange;
import org.taranco.vo.Money;

import java.time.Instant;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class BookingSnapshot {

    private BookingId bookingId;
    private CustomerId customerId;
    private HotelId hotelId;
    private String hotelName;
    private Set<RoomSnapshot> rooms = new HashSet<>();
    private DateRange bookingPeriod;
    private Instant bookingDate;
    private Instant paymentDate;
    private Money price;
    private BookingStatus status;

    BookingSnapshot(BookingId bookingId, CustomerId customerId, HotelId hotelId, String hotelName, Set<RoomSnapshot> rooms, DateRange bookingPeriod, Instant bookingDate, Instant paymentDate, Money price, BookingStatus status) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.rooms.addAll(rooms);
        this.bookingPeriod = bookingPeriod;
        this.bookingDate = bookingDate;
        this.paymentDate = paymentDate;
        this.price = price;
        this.status = status;
    }

    public BookingSnapshot() {
    }

    public CustomerId getCustomerId() {
        return customerId;
    }

    public BookingId getBookingId() {
        return bookingId;
    }

    public HotelId getHotelId() {
        return hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public Set<RoomSnapshot> getRooms() {
        return Collections.unmodifiableSet(rooms);
    }

    public DateRange getBookingPeriod() {
        return bookingPeriod;
    }

    public Instant getBookingDate() {
        return bookingDate;
    }

    public Instant getPaymentDate() {
        return paymentDate;
    }

    public Money getPrice() {
        return price;
    }

    public BookingStatus getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingSnapshot that = (BookingSnapshot) o;
        return Objects.equals(bookingId, that.bookingId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingId);
    }
}
