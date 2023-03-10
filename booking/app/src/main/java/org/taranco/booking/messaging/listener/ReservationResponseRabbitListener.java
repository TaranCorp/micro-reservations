package org.taranco.booking.messaging.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.taranco.booking.ReservationResponseListener;
import org.taranco.booking.dto.ReservationResponse;

@Component
class ReservationResponseRabbitListener {

    private final ReservationResponseListener reservationResponseListener;

    ReservationResponseRabbitListener(ReservationResponseListener reservationResponseListener) {
        this.reservationResponseListener = reservationResponseListener;
    }

    @RabbitListener(queues = "${topics.reservation.response}")
    public void handleReservationResponse(ReservationResponse response) {
        try {
            if (response.getState() == ReservationResponse.State.COMPLETED) {
                reservationResponseListener.reservationCompleted(response);
            }
            reservationResponseListener.reservationCancelled(response);
        } catch (Exception e) { // workaround caused by rolling back event to rabbitmq in case when exception occurs
            System.out.println(e.getMessage());
        }
    }
}
