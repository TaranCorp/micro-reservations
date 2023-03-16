package org.taranco.hotel.vo;

import org.taranco.HotelId;
import org.taranco.TimesheetId;
import org.taranco.vo.DateRange;
import org.taranco.vo.Money;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class ReservationResponse {
    private final Money totalPrice = new Money(BigDecimal.ZERO);
    private final Set<TimesheetId> timesheetIds = new HashSet<>();
    private DateRange bookingPeriod;
    private final HotelId hotelId;
    private final String hotelName;

    public ReservationResponse(HotelId hotelId, String name) {
        this.hotelName = name;
        this.hotelId = hotelId;
    }

    public void addTimesheetId(TimesheetId roomId) {
        timesheetIds.add(roomId);
    }

    public void sumTotal(Money money) {
        this.totalPrice.add(money);
    }

    public void setBookingPeriod(DateRange bookingPeriod) {
        this.bookingPeriod = bookingPeriod;
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
