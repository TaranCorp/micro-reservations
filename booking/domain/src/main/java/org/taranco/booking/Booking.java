package org.taranco.booking;

import org.taranco.BookingId;
import org.taranco.HotelId;
import org.taranco.RoomId;
import org.taranco.vo.DateRange;
import org.taranco.vo.Money;

import java.time.Instant;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static java.util.Collections.unmodifiableSet;

class Booking {
    private final BookingId bookingId;
    private HotelId hotelId;
    private String hotelName;
    private Set<Room> rooms = new HashSet<>();
    private final DateRange bookingPeriod;
    private Instant bookingDate;
    private Instant paymentDate;
    private Money price;
    private BookingStatus status;

    Booking(BookingId bookingId, DateRange bookingPeriod, HotelId hotelId, Set<Room> providedRooms) {
        if (hotelId == null) {
            throw new IllegalArgumentException("Hotel id cannot be null");
        }
        addRooms(providedRooms);
        this.bookingId = bookingId;
        this.bookingPeriod = bookingPeriod;
        this.status = BookingStatus.PENDING;
    }

    Booking reserve(String hotelName) {
        if (hotelName == null || hotelName.isBlank()) {
            throw new IllegalArgumentException("Hotel id cannot be null");
        }
        changeStatus(BookingStatus.RESERVED);
        this.hotelName = hotelName;
        return this;
    }

    Booking cancel() {
        changeStatus(BookingStatus.CANCELLED);
        rooms = Collections.emptySet();
        hotelId = null;
        hotelName = null;
        return this;
    }

    Booking pay(Money price, Instant paymentDate) {
        changeStatus(BookingStatus.PAID);
        this.paymentDate = paymentDate;
        this.price = price;
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

    Set<Room> rooms() {
        return unmodifiableSet(rooms);
    }

    static class Room {
        static Room create(RoomId roomId, int vacancies) {
            return new Room(roomId, vacancies);
        }

        private final RoomId roomId;
        private final int vacancies;

        private Room(RoomId roomId, int vacancies) {
            if (roomId == null) {
                throw new IllegalArgumentException("Room id cannot be null");
            }
            if (vacancies <= 0) {
                throw new IllegalArgumentException("Cannot book empty rooms");
            }
            this.roomId = roomId;
            this.vacancies = vacancies;
        }
    }
}
