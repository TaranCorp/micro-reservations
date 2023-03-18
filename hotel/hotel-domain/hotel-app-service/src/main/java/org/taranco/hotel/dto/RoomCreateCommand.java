package org.taranco.hotel.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class RoomCreateCommand {
    private int number;
    private BigDecimal price;
    private String bedsType;
    private int beds;

    @JsonCreator
    public RoomCreateCommand(
            @JsonProperty("number") int number,
            @JsonProperty("price") BigDecimal price,
            @JsonProperty("bedsType") String bedsType,
            @JsonProperty("beds") int beds) {
        this.number = number;
        this.price = price;
        this.bedsType = bedsType;
        this.beds = beds;
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

    public void setNumber(int number) {
        this.number = number;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setBedsType(String bedsType) {
        this.bedsType = bedsType;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }
}
