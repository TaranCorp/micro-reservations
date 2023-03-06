package org.taranco.booking.dto;

import org.taranco.BookingId;
import org.taranco.ReservationId;
import org.taranco.vo.Money;

public class ReservationResponse {

    public enum State {
        RESERVED, CANCELLED;
    }

    private final BookingId bookingId;
    private final ReservationId reservationId;
    private final String hotelName;
    private final Money price;
    private final State state;

    public ReservationResponse(State state, BookingId bookingId, ReservationId reservationId, String hotelName, Money price) {
        this.state = state;
        this.bookingId = bookingId;
        this.reservationId = reservationId;
        this.hotelName = hotelName;
        this.price = price;
    }

    public BookingId bookingId() {
        return bookingId;
    }

    public ReservationId reservationId() {
        return reservationId;
    }

    public String hotelName() {
        return hotelName;
    }

    public Money price() {
        return price;
    }

    public State state() {
        return state;
    }
}
