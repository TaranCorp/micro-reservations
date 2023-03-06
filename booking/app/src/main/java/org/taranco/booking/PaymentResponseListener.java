package org.taranco.booking;

import org.taranco.booking.dto.PaymentResponse;

public interface PaymentResponseListener {
    void paymentCompleted(PaymentResponse response);

    void paymentCancelled(PaymentResponse response);
}
