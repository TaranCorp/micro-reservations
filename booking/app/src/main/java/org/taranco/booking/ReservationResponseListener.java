package org.taranco.booking;

import org.taranco.booking.dto.ReservationResponse;

public interface ReservationResponseListener {
    void reservationCompleted(ReservationResponse response);

    void reservationCancelled(ReservationResponse response);
}
