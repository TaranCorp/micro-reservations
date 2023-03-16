package org.taranco.hotel.port.output;

import org.taranco.DomainEventPublisher;
import org.taranco.hotel.dto.BookedRoomsResponse;

public interface BookedRoomsResponsePublisher extends DomainEventPublisher<BookedRoomsResponse> {
}
