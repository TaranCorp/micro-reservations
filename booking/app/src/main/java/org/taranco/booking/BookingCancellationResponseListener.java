package org.taranco.booking;

import org.taranco.booking.dto.CancellationResponse;

public interface BookingCancellationResponseListener {
    void cancellationCompleted(CancellationResponse cancellationResponse);
}
