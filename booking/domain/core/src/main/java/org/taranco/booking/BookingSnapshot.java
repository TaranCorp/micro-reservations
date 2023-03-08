package org.taranco.booking;

import org.taranco.BookingId;
import org.taranco.CustomerId;
import org.taranco.HotelId;
import org.taranco.vo.DateRange;
import org.taranco.vo.Money;

import java.time.Instant;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

class BookingSnapshot {

    private final BookingId bookingId;
    private final CustomerId customerId;
    private final HotelId hotelId;
    private final String hotelName;
    private final Set<Room> rooms = new HashSet<>();
    private final DateRange bookingPeriod;
    private final Instant bookingDate;
    private final Instant paymentDate;
    private final Money price;
    private final BookingStatus status;

    BookingSnapshot(BookingId bookingId, CustomerId customerId, HotelId hotelId, String hotelName, Set<Room> rooms, DateRange bookingPeriod, Instant bookingDate, Instant paymentDate, Money price, BookingStatus status) {
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

    public CustomerId customerId() {
        return customerId;
    }

    public BookingId bookingId() {
        return bookingId;
    }

    public HotelId hotelId() {
        return hotelId;
    }

    public String hotelName() {
        return hotelName;
    }

    public Set<Room> rooms() {
        return Collections.unmodifiableSet(rooms);
    }

    public DateRange bookingPeriod() {
        return bookingPeriod;
    }

    public Instant bookingDate() {
        return bookingDate;
    }

    public Instant paymentDate() {
        return paymentDate;
    }

    public Money price() {
        return price;
    }

    public BookingStatus status() {
        return status;
    }
}
