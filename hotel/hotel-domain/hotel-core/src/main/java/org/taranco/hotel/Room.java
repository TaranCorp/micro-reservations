package org.taranco.hotel;

import org.taranco.RoomId;
import org.taranco.vo.Money;

public class Room {
    public static Room restore(RoomSnapshot roomSnapshot) {
        return new Room(roomSnapshot.getRoomId(), roomSnapshot.getNumber(), roomSnapshot.getPrice(), roomSnapshot.getBedsType(), roomSnapshot.getBeds());
    }

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

    Money getTotalPrice(int places) {
        return price.multiply(places);
    }

    int getAvailableSpots() {
        return beds * bedsType.getPersonAllowed();
    }

    public RoomSnapshot getSnapshot() {
        return new RoomSnapshot(
                roomId,
                number,
                price,
                bedsType,
                beds
        );
    }
}
