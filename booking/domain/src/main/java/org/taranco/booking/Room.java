package org.taranco.booking;

import org.taranco.RoomId;

class Room {
    static Room create(RoomId roomId, int vacancies) {
        return new Room(roomId, vacancies);
    }

    private final RoomId roomId;
    private final int vacancies;

    private Room(RoomId roomId, int vacancies) {
        if (roomId == null) {
            throw new IllegalArgumentException("Room id cannot be null");
        }
        if (vacancies <= 0) {
            throw new IllegalArgumentException("Cannot book empty rooms");
        }
        this.roomId = roomId;
        this.vacancies = vacancies;
    }
}
