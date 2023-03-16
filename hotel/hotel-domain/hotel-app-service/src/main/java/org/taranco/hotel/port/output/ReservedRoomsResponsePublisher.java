package org.taranco.hotel.port.output;

import org.taranco.DomainEventPublisher;
import org.taranco.hotel.dto.ReservedRoomsResponse;

public interface ReservedRoomsResponsePublisher extends DomainEventPublisher<ReservedRoomsResponse> {
}
