package org.taranco.booking.dto;

import org.taranco.RoomId;

import java.util.UUID;

public class RoomTracker {
    private UUID roomId;
    private int vacancies;

    public RoomTracker(RoomId roomId, int vacancies) {
        this.roomId = roomId == null ? null : roomId.getId();
        this.vacancies = vacancies;
    }

    public RoomTracker() {
    }

    public UUID getRoomId() {
        return roomId;
    }

    public int getVacancies() {
        return vacancies;
    }
}
