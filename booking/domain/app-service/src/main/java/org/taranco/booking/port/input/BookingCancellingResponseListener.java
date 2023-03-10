package org.taranco.booking.port.input;

import org.taranco.booking.dto.CancellationResponse;

public interface BookingCancellingResponseListener {
    void cancellationCompleted(CancellationResponse cancellationResponse);
}
