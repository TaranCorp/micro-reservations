package org.taranco.hotel.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.taranco.hotel.dto.ReservedRoomsResponse;
import org.taranco.hotel.port.output.ReservedRoomsResponsePublisher;

@Component
public class ReservedRoomsResponseRabbitPublisher implements ReservedRoomsResponsePublisher {

    private final RabbitTemplate rabbitTemplate;

    public ReservedRoomsResponseRabbitPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void publish(ReservedRoomsResponse event) {
        rabbitTemplate.convertAndSend(event);
    }
}
