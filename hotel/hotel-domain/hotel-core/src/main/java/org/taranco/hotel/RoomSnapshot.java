package org.taranco.hotel;

import org.taranco.RoomId;
import org.taranco.vo.Money;

public class RoomSnapshot {
    private RoomId roomId;
    private int number;
    private Money price;
    private BedType bedsType;
    private int beds;

    public RoomSnapshot(RoomId roomId, int number, Money price, BedType bedsType, int beds) {
        this.roomId = roomId;
        this.number = number;
        this.price = price;
        this.bedsType = bedsType;
        this.beds = beds;
    }

    protected RoomSnapshot() {
    }

    public RoomId getRoomId() {
        return roomId;
    }

    public int getNumber() {
        return number;
    }

    public Money getPrice() {
        return price;
    }

    public BedType getBedsType() {
        return bedsType;
    }

    public int getBeds() {
        return beds;
    }
}
