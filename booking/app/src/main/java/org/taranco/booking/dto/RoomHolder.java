package org.taranco.booking.dto;

import org.taranco.RoomId;

public class RoomHolder {
    private final RoomId roomId;
    private final int vacancies;

    public RoomHolder(RoomId roomId, int vacancies) {
        this.roomId = roomId;
        this.vacancies = vacancies;
    }

    public RoomId roomId() {
        return roomId;
    }

    public int vacancies() {
        return vacancies;
    }
}
