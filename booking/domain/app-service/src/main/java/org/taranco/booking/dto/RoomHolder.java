package org.taranco.booking.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.taranco.RoomId;

public class RoomHolder {
    private final RoomId roomId;
    private final int vacancies;

    @JsonCreator
    public RoomHolder(
            @JsonProperty("roomId") RoomId roomId,
            @JsonProperty("vacancies") int vacancies) {
        this.roomId = roomId;
        this.vacancies = vacancies;
    }

    public RoomId getRoomId() {
        return roomId;
    }

    public int getVacancies() {
        return vacancies;
    }
}
