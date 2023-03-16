package org.taranco.hotel.dto;

import org.taranco.BookingId;
import org.taranco.DomainEvent;
import org.taranco.HotelId;
import org.taranco.TimesheetId;
import org.taranco.vo.DateRange;
import org.taranco.vo.Money;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

public class ReservedRoomsResponse implements DomainEvent {
    private final Instant occurredOn;
    private final Money totalPrice = new Money(BigDecimal.ZERO);
    private final Set<TimesheetId> timesheetIds = new HashSet<>();
    private final DateRange bookingPeriod;
    private final HotelId hotelId;
    private final String hotelName;
    private final Instant reservationDate;
    private final BookingId bookingId;

    public ReservedRoomsResponse(Instant occurredOn, DateRange bookingPeriod, HotelId hotelId, String hotelName, Set<TimesheetId> timesheetIds, Money totalPrice, Instant reservationDate, BookingId bookingId) {
        this.occurredOn = occurredOn;
        this.bookingPeriod = bookingPeriod;
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.reservationDate = reservationDate;
        this.bookingId = bookingId;
        this.timesheetIds.addAll(timesheetIds);
        this.totalPrice.add(totalPrice);
    }

    @Override
    public Instant getOccurredOn() {
        return occurredOn;
    }

    public Instant getReservationDate() {
        return reservationDate;
    }

    public BookingId getBookingId() {
        return bookingId;
    }

    public Money getTotalPrice() {
        return totalPrice;
    }

    public Set<TimesheetId> getTimesheetIds() {
        return timesheetIds;
    }

    public DateRange getBookingPeriod() {
        return bookingPeriod;
    }

    public HotelId getHotelId() {
        return hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }
}
