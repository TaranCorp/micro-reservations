package org.taranco.booking.dto;

import org.taranco.BookingId;
import org.taranco.PaymentId;
import org.taranco.vo.Money;

import java.time.Instant;

public class PaymentResponse {
    private final BookingId bookingId;
    private final Money price;
    private final PaymentId paymentId;
    private final Instant paymentDate;

    public PaymentResponse(BookingId bookingId, Money price, PaymentId paymentId, Instant paymentDate) {
        this.bookingId = bookingId;
        this.price = price;
        this.paymentId = paymentId;
        this.paymentDate = paymentDate;
    }

    public BookingId bookingId() {
        return bookingId;
    }

    public Money price() {
        return price;
    }

    public PaymentId paymentId() {
        return paymentId;
    }

    public Instant paymentDate() {
        return paymentDate;
    }
}
