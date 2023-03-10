package org.taranco.booking.port.input;

import org.taranco.booking.dto.ReservationResponse;

public interface ReservationResponseListener {
    void reservationCompleted(ReservationResponse response);

    void reservationCancelled(ReservationResponse response);
}
