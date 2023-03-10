package org.taranco.payment;

import org.taranco.booking.dto.BookingReservedEvent;
import org.taranco.booking.port.output.PaymentEventPublisher;

public class PaymentCreator {
    private final PaymentEventPublisher paymentEventPublisher;

    PaymentCreator(PaymentEventPublisher paymentEventPublisher) {
        this.paymentEventPublisher = paymentEventPublisher;
    }

    public void publishPaymentRequest(BookingReservedEvent bookingReservedEvent) {
        if (bookingReservedEvent == null) {
            throw new IllegalArgumentException("Cannot process null BookingReservedEvent");
        }

        paymentEventPublisher.publish(bookingReservedEvent);
    }
}
