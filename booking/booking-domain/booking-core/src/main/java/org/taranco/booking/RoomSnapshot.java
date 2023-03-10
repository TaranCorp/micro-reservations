package org.taranco.booking;

import org.taranco.RoomId;

import java.util.Objects;

class RoomSnapshot {
    private RoomId roomId;
    private int vacancies;

    public RoomSnapshot() {
    }

    public RoomSnapshot(RoomId roomId, int vacancies) {
        this.roomId = roomId;
        this.vacancies = vacancies;
    }

    public RoomId getRoomId() {
        return roomId;
    }

    public int getVacancies() {
        return vacancies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomSnapshot that = (RoomSnapshot) o;
        return Objects.equals(roomId, that.roomId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId);
    }
}
