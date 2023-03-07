package org.taranco.booking;

import org.taranco.booking.dto.CancellationResponse;

public interface BookingCancellingResponseListener {
    void cancellationCompleted(CancellationResponse cancellationResponse);
}
