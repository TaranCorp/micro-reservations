package org.taranco.hotel;

import org.taranco.RoomId;
import org.taranco.vo.Money;

public class Room {
    static Room create(RoomId roomId, int number, Money price, BedType bedsType, int beds) {
        return new Room(roomId, number, price, bedsType, beds);
    }

    private RoomId roomId;
    private int number;
    private Money price;
    private BedType bedsType;
    private int beds;

    private Room(RoomId roomId, int number, Money price, BedType bedsType, int beds) {
        this.roomId = roomId;
        this.number = number;
        this.price = price;
        this.bedsType = bedsType;
        this.beds = beds;
    }

    public RoomId getRoomId() {
        return roomId;
    }

    Money getTotalPrice(int places) {
        return price.multiply(places);
    }

    int getAvailableSpots() {
        return beds * bedsType.getPersonAllowed();
    }
}
