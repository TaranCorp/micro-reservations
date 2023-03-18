package org.taranco.hotel.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class CreatedRoomResponse {
    private UUID roomId;
    private int number;
    private BigDecimal price;
    private String bedsType;
    private int beds;

    public CreatedRoomResponse(UUID roomId, int number, BigDecimal price, String bedsType, int beds) {
        this.roomId = roomId;
        this.number = number;
        this.price = price;
        this.bedsType = bedsType;
        this.beds = beds;
    }

    public CreatedRoomResponse() {
    }

    public UUID getRoomId() {
        return roomId;
    }

    public int getNumber() {
        return number;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getBedsType() {
        return bedsType;
    }

    public int getBeds() {
        return beds;
    }
}
