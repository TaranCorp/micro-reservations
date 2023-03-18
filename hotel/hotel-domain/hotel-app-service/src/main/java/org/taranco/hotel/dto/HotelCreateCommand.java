package org.taranco.hotel.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public class HotelCreateCommand {
    private String name;
    private Set<RoomCreateCommand> roomCreateCommands;

    @JsonCreator
    public HotelCreateCommand(
            @JsonProperty("name") String name,
            @JsonProperty("roomCreateCommands") Set<RoomCreateCommand> roomCreateCommands) {
        this.name = name;
        this.roomCreateCommands = roomCreateCommands;
    }

    public String getName() {
        return name;
    }

    public Set<RoomCreateCommand> getRoomCreateCommands() {
        return roomCreateCommands;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRoomCreateCommands(Set<RoomCreateCommand> roomCreateCommands) {
        this.roomCreateCommands = roomCreateCommands;
    }
}
