package org.taranco.hotel.dto;

import java.util.Set;
import java.util.UUID;

public class CreatedHotelResponse {
    private UUID hotelId;
    private String name;
    private Set<CreatedRoomResponse> createdRoomResponses;

    public CreatedHotelResponse(UUID hotelId, String name, Set<CreatedRoomResponse> createdRoomResponses) {
        this.hotelId = hotelId;
        this.name = name;
        this.createdRoomResponses = createdRoomResponses;
    }

    public CreatedHotelResponse() {
    }

    public UUID getHotelId() {
        return hotelId;
    }

    public String getName() {
        return name;
    }

    public Set<CreatedRoomResponse> getCreatedRoomResponses() {
        return createdRoomResponses;
    }
}
