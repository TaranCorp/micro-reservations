package org.taranco.hotel;

import org.taranco.HotelId;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class HotelSnapshot {
    private HotelId hotelId;
    private String name;
    private Set<RoomSnapshot> rooms  = new HashSet<>();
    private Set<TimesheetSnapshot> timesheets  = new HashSet<>();

    public HotelSnapshot(HotelId hotelId, String name, Set<RoomSnapshot> rooms, Set<TimesheetSnapshot> timesheets) {
        this.hotelId = hotelId;
        this.name = name;
        this.rooms.addAll(rooms);
        this.timesheets.addAll(timesheets);
    }

    protected HotelSnapshot() {
    }

    public HotelId getHotelId() {
        return hotelId;
    }

    public String getName() {
        return name;
    }

    public Set<RoomSnapshot> getRooms() {
        return Collections.unmodifiableSet(rooms);
    }

    public Set<TimesheetSnapshot> getTimesheets() {
        return Collections.unmodifiableSet(timesheets);
    }
}
