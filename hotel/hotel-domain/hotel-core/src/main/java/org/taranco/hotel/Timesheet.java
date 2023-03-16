package org.taranco.hotel;

import org.taranco.CustomerId;
import org.taranco.TimesheetId;
import org.taranco.vo.DateRange;
import org.taranco.vo.Money;

class Timesheet {
    private TimesheetId timesheetId;
    private CustomerId ownerId;
    private Money totalPrice;
    private DateRange bookingPeriod;
    private RoomStatus status;

    public Timesheet(TimesheetId timesheetId, CustomerId ownerId, Money totalPrice, DateRange bookingPeriod, RoomStatus status) {
        this.timesheetId = timesheetId;
        this.ownerId = ownerId;
        this.totalPrice = totalPrice;
        this.bookingPeriod = bookingPeriod;
        this.status = status;
    }

    public TimesheetId getTimesheetId() {
        return timesheetId;
    }

    public CustomerId getOwnerId() {
        return ownerId;
    }

    public Money getTotalPrice() {
        return totalPrice;
    }

    public DateRange getBookingPeriod() {
        return bookingPeriod;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }
}
