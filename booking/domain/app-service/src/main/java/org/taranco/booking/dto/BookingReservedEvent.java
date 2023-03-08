package org.taranco.booking.dto;

import org.taranco.BookingId;
import org.taranco.CustomerId;
import org.taranco.DomainEvent;
import org.taranco.vo.Money;

import java.time.Instant;

public class BookingReservedEvent implements DomainEvent {
    private final Instant occurredOn;
    private final BookingId bookingId;
    private final Money price;
    private final CustomerId customerId;

    public BookingReservedEvent(BookingId bookingId, Money price, CustomerId customerId) {
        this.bookingId = bookingId;
        this.price = price;
        this.customerId = customerId;
        this.occurredOn = Instant.now();
    }

    @Override
    public Instant getOccurredOn() {
        return occurredOn;
    }

    public BookingId bookingId() {
        return bookingId;
    }

    public Money price() {
        return price;
    }

    public CustomerId customerId() {
        return customerId;
    }
}
