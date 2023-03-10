package org.taranco.booking.messaging.publisher;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.taranco.booking.dto.BookingCreatedEvent;
import org.taranco.booking.port.output.ReservationEventPublisher;

@Component
class ReservationRequestRabbitPublisher implements ReservationEventPublisher {

    @Value("${topics.reservation.request}")
    private String reservationTopic;

    private final RabbitTemplate rabbitTemplate;

    public ReservationRequestRabbitPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void publish(BookingCreatedEvent event) {
        rabbitTemplate.convertAndSend(reservationTopic, event);
    }
}
