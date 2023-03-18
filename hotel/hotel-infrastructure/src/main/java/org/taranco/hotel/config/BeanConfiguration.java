package org.taranco.hotel.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.taranco.hotel.HotelApplicationServiceImpl;
import org.taranco.hotel.RoomBookingRequestListenerImpl;
import org.taranco.hotel.RoomReservationRequestListenerImpl;
import org.taranco.hotel.port.input.HotelApplicationService;
import org.taranco.hotel.port.input.RoomBookingRequestListener;
import org.taranco.hotel.port.input.RoomReservationRequestListener;
import org.taranco.hotel.port.output.HotelRepository;

@Configuration
class BeanConfiguration {

    @Value("${topics.reservation.request}")
    private String reservationRequestTopic;

    @Value("${topics.reservation.response}")
    private String reservationResponseTopic;

    @Bean
    HotelApplicationService hotelApplicationService(HotelRepository hotelRepository) {
        return new HotelApplicationServiceImpl(hotelRepository, null, null, null);
    }

    @Bean
    RoomBookingRequestListener roomBookingRequestListener(HotelRepository hotelRepository) {
        return new RoomBookingRequestListenerImpl(hotelApplicationService(hotelRepository));
    }

    @Bean
    RoomReservationRequestListener roomReservationRequestListener(HotelRepository hotelRepository) {
        return new RoomReservationRequestListenerImpl(hotelApplicationService(hotelRepository));
    }

    @Bean
    Queue reservationRequestQueue() {
        return new Queue(reservationRequestTopic, false);
    }

    @Bean
    Queue reservationResponseQueue() {
        return new Queue(reservationResponseTopic, false);
    }

    @Bean
    MessageConverter messageConverter() {
        ObjectMapper objectMapper = new JsonMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return new Jackson2JsonMessageConverter(objectMapper);
    }
}
