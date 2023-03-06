package org.taranco.booking;

import org.taranco.BookingId;
import org.taranco.HotelId;
import org.taranco.vo.DateRange;
import org.taranco.vo.Money;

import java.time.Instant;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static java.util.Collections.unmodifiableSet;

class Booking {
    static Booking restore(BookingSnapshot snapshot) {
        return new Booking(
                snapshot.bookingId(),
                snapshot.hotelId(),
                snapshot.hotelName(),
                snapshot.rooms(),
                snapshot.bookingPeriod(),
                snapshot.bookingDate(),
                snapshot.paymentDate(),
                snapshot.price(),
                snapshot.status()
        );
    }

    private final BookingId bookingId;
    private HotelId hotelId;
    private String hotelName;
    private Set<Room> rooms = new HashSet<>();
    private final DateRange bookingPeriod;
    private Instant bookingDate;
    private Instant paymentDate;
    private Money price;
    private BookingStatus status;

    Booking(BookingId bookingId, DateRange bookingPeriod, HotelId hotelId, Set<Room> rooms) {
        if (hotelId == null) {
            throw new IllegalArgumentException("Hotel id cannot be null");
        }
        addRooms(rooms);
        this.bookingId = bookingId;
        this.bookingPeriod = bookingPeriod;
        this.status = BookingStatus.PENDING;
    }

    Booking(BookingId bookingId, HotelId hotelId, String hotelName, Set<Room> rooms, DateRange bookingPeriod, Instant bookingDate, Instant paymentDate, Money price, BookingStatus status) {
        this.bookingId = bookingId;
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.rooms = rooms;
        this.bookingPeriod = bookingPeriod;
        this.bookingDate = bookingDate;
        this.paymentDate = paymentDate;
        this.price = price;
        this.status = status;
    }

    Booking reserve(String hotelName, Money price) {
        if (hotelName == null || hotelName.isBlank()) {
            throw new IllegalArgumentException("Hotel id cannot be null");
        }
        changeStatus(BookingStatus.RESERVED);
        this.price = price;
        this.hotelName = hotelName;
        return this;
    }

    Booking cancel() {
        changeStatus(BookingStatus.CANCELLED);
        rooms = Collections.emptySet();
        hotelId = null;
        hotelName = null;
        price = null;
        return this;
    }

    Booking pay(Instant paymentDate) {
        changeStatus(BookingStatus.PAID);
        this.paymentDate = paymentDate;
        return this;
    }

    Booking book(Instant bookingDate) {
        changeStatus(BookingStatus.APPROVED);
        this.bookingDate = bookingDate;
        return this;
    }

    Booking initCancel() {
        changeStatus(BookingStatus.CANCELLING);
        return this;
    }

    private void changeStatus(BookingStatus next) {
        if (!BookingStatus.nextStates(this.status).contains(next)) {
            throw new IllegalArgumentException("Cannot change status from %s to %s".formatted(this.status.name(), next.name()));
        }
        this.status = next;
    }

    private void addRooms(Set<Room> providedRooms) {
        this.rooms.addAll(providedRooms);
    }

    BookingSnapshot getSnapshot() {
        return new BookingSnapshot(
                bookingId,
                hotelId,
                hotelName,
                rooms,
                bookingPeriod,
                bookingDate,
                paymentDate,
                price,
                status
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(bookingId, booking.bookingId) && Objects.equals(hotelId, booking.hotelId) && Objects.equals(hotelName, booking.hotelName) && Objects.equals(rooms, booking.rooms) && Objects.equals(bookingPeriod, booking.bookingPeriod) && Objects.equals(bookingDate, booking.bookingDate) && Objects.equals(paymentDate, booking.paymentDate) && Objects.equals(price, booking.price) && status == booking.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingId, hotelId, hotelName, rooms, bookingPeriod, bookingDate, paymentDate, price, status);
    }
}
