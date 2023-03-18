package org.taranco.hotel;

import org.taranco.CustomerId;
import org.taranco.TimesheetId;
import org.taranco.vo.DateRange;
import org.taranco.vo.Money;

public class TimesheetSnapshot {
    private TimesheetId timesheetId;
    private CustomerId customerId;
    private Money totalPrice;
    private DateRange bookingPeriod;
    private RoomStatus status;

    public TimesheetSnapshot(TimesheetId timesheetId, CustomerId customerId, Money totalPrice, DateRange bookingPeriod, RoomStatus status) {
        this.timesheetId = timesheetId;
        this.customerId = customerId;
        this.totalPrice = totalPrice;
        this.bookingPeriod = bookingPeriod;
        this.status = status;
    }

    protected TimesheetSnapshot() {
    }

    public TimesheetId getTimesheetId() {
        return timesheetId;
    }

    public CustomerId getCustomerId() {
        return customerId;
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
}
