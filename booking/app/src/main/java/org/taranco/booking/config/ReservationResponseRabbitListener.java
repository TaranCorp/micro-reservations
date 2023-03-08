package org.taranco.booking.config;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.taranco.ReservationId;
import org.taranco.booking.ReservationResponseListener;
import org.taranco.booking.dto.BookingCreatedEvent;
import org.taranco.booking.dto.ReservationResponse;
import org.taranco.vo.Money;

import java.math.BigDecimal;

@Component
class ReservationResponseRabbitListener {

    private final ReservationResponseListener reservationResponseListener;

    ReservationResponseRabbitListener(ReservationResponseListener reservationResponseListener) {
        this.reservationResponseListener = reservationResponseListener;
    }

    @RabbitListener(queues = "${topics.reservation.request}")
    public void handleReservationResponse(BookingCreatedEvent response) {
        try {
            reservationResponseListener.reservationCompleted(
                    new ReservationResponse(
                            ReservationResponse.State.RESERVED,
                            response.getBookingId(),
                            new ReservationId(response.getBookingId().getId()),
                            "hotel",
                            new Money(BigDecimal.TEN)
                    )
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
