package org.taranco.payment;

import org.taranco.booking.dto.BookingReservedEvent;

class PaymentCreator {
    private final PaymentEventPublisher paymentEventPublisher;

    PaymentCreator(PaymentEventPublisher paymentEventPublisher) {
        this.paymentEventPublisher = paymentEventPublisher;
    }

    void publishPaymentRequest(BookingReservedEvent bookingReservedEvent) {
        if (bookingReservedEvent == null) {
            throw new IllegalArgumentException("Cannot process null BookingReservedEvent");
        }

        paymentEventPublisher.publish(bookingReservedEvent);
    }
}
