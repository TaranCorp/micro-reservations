package org.taranco.hotel.port.output;

import org.taranco.DomainEventPublisher;
import org.taranco.hotel.dto.ReleasedRoomsResponse;

public interface ReleasedRoomsResponsePublisher extends DomainEventPublisher<ReleasedRoomsResponse> {
}
