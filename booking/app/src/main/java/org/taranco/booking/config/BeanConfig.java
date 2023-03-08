package org.taranco.booking.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.taranco.booking.BookingApplicationService;
import org.taranco.booking.BookingApplicationServiceImpl;
import org.taranco.booking.BookingRepository;
import org.taranco.booking.ReservationResponseListener;
import org.taranco.booking.ReservationResponseListenerImpl;
import org.taranco.hotel.ReservationCreator;
import org.taranco.hotel.ReservationEventPublisher;
import org.taranco.payment.PaymentCreator;

@Configuration
class BeanConfig {

    @Value("${topics.reservation.request}")
    private String reservationRequestTopic;

    @Value("${topics.reservation.response}")
    private String reservationResponseTopic;

    @Bean
    PaymentCreator paymentCreator() {
        return null;
    }

    @Bean
    ReservationResponseListener reservationResponseListener(BookingRepository bookingRepository) {
        return new ReservationResponseListenerImpl(bookingRepository, paymentCreator());
    }

    @Bean
    ReservationCreator reservationCreator(ReservationEventPublisher publisher) {
        return new ReservationCreator(publisher);
    }

    @Bean
    BookingApplicationService bookingApplicationService(BookingRepository bookingRepository, ReservationCreator reservationCreator) {
        return new BookingApplicationServiceImpl(bookingRepository, reservationCreator);
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
