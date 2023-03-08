package org.taranco.booking.config;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.taranco.booking.dto.BookingCreatedEvent;
import org.taranco.hotel.ReservationEventPublisher;

@Component
public class ReservationDomainEventPublisherImpl implements ReservationEventPublisher {

    @Value("${topics.reservation.request}")
    private String reservationTopic;

    private final RabbitTemplate rabbitTemplate;

    public ReservationDomainEventPublisherImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void publish(BookingCreatedEvent event) {
        rabbitTemplate.convertAndSend(reservationTopic, event);
    }
}
