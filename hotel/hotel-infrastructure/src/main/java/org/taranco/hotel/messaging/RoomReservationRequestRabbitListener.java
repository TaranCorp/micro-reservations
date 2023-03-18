package org.taranco.hotel.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.taranco.booking.dto.BookingCreatedEvent;
import org.taranco.hotel.port.input.RoomReservationRequestListener;
import org.taranco.hotel.vo.ReserveRoomCommand;
import org.taranco.hotel.vo.ReserveRoomCommand.RoomReservationDetails;

import java.util.stream.Collectors;

@Component
class RoomReservationRequestRabbitListener {
    private static final Logger log = LoggerFactory.getLogger(RoomReservationRequestRabbitListener.class);

    private final RoomReservationRequestListener roomReservationRequestListener;

    RoomReservationRequestRabbitListener(RoomReservationRequestListener roomReservationRequestListener) {
        this.roomReservationRequestListener = roomReservationRequestListener;
    }

    @RabbitListener(queues = "${topics.reservation.request}")
    void handleReservationRequests(BookingCreatedEvent bookingCreatedEvent) {
        try {
            roomReservationRequestListener.reserveRooms(
                    new ReserveRoomCommand(
                            bookingCreatedEvent.getCustomerId(),
                            bookingCreatedEvent.getBookingPeriod(),
                            bookingCreatedEvent.getRooms().stream()
                                    .map(roomHolder -> new RoomReservationDetails(
                                            roomHolder.getRoomId(),
                                            roomHolder.getVacancies())
                                    )
                                    .collect(Collectors.toSet())
                    ),
                    bookingCreatedEvent.getHotelId(),
                    bookingCreatedEvent.getBookingId()
            );
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }
}
