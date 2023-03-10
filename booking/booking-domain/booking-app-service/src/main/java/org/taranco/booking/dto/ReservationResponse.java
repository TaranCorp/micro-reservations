package org.taranco.booking.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.taranco.BookingId;
import org.taranco.ReservationId;
import org.taranco.vo.Money;

public class ReservationResponse {

    public enum State {
        COMPLETED, CANCELLED
    }

    private final BookingId bookingId;
    private final ReservationId reservationId;
    private final String hotelName;
    private final Money price;
    private final State state;

    @JsonCreator
    public ReservationResponse(
            @JsonProperty("bookingId") BookingId bookingId,
            @JsonProperty("reservationId") ReservationId reservationId,
            @JsonProperty("hotelName") String hotelName,
            @JsonProperty("price") Money price,
            @JsonProperty("state") State state
    ) {
        this.bookingId = bookingId;
        this.reservationId = reservationId;
        this.hotelName = hotelName;
        this.price = price;
        this.state = state;
    }

    public BookingId getBookingId() {
        return bookingId;
    }

    public ReservationId getReservationId() {
        return reservationId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public Money getPrice() {
        return price;
    }

    public State getState() {
        return state;
    }
}
