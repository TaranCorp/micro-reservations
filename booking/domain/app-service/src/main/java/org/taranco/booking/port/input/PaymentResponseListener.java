package org.taranco.booking.port.input;

import org.taranco.booking.dto.PaymentResponse;

public interface PaymentResponseListener {
    void paymentCompleted(PaymentResponse response);

    void paymentCancelled(PaymentResponse response);
}
