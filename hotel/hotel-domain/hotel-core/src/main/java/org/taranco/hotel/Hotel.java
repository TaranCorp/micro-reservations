package org.taranco.hotel;

import org.taranco.CustomerId;
import org.taranco.HotelId;
import org.taranco.NotFoundException;
import org.taranco.RoomId;
import org.taranco.TimesheetId;
import org.taranco.hotel.vo.ReservationResponse;
import org.taranco.hotel.vo.ReserveRoomCommand;
import org.taranco.vo.DateRange;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class Hotel {
    public static Hotel restore(HotelSnapshot snapshot) {
        return new Hotel(
                snapshot.getHotelId(),
                snapshot.getName(),
                snapshot.getRooms().stream()
                        .map(Room::restore)
                        .collect(Collectors.toSet()),
                snapshot.getTimesheets().stream()
                        .map(Timesheet::restore)
                        .collect(Collectors.toSet())
        );
    }

    static Hotel create(HotelId hotelId, String name, Set<Room> rooms) {
        if (rooms.isEmpty()) {
            throw new IllegalArgumentException("Cannot create Hotel without rooms");
        }
        if (rooms.size() != rooms.stream().map(room -> room.getSnapshot().getNumber()).collect(Collectors.toSet()).size()) {
            throw new IllegalArgumentException("Rooms numbers must be unique");
        }
        return new Hotel(hotelId, name, rooms);
    }
    private final HotelId hotelId;
    private final String name;
    private final Set<Room> rooms = new HashSet<>();
    private final Set<Timesheet> timesheets = new HashSet<>();

    private Hotel(HotelId hotelId, String name, Set<Room> rooms) {
        this.hotelId = hotelId;
        this.name = name;
        this.rooms.addAll(rooms);
    }

    private Hotel(HotelId hotelId, String name, Set<Room> rooms, Set<Timesheet> timesheets) {
        this.hotelId = hotelId;
        this.name = name;
        this.rooms.addAll(rooms);
        this.timesheets.addAll(timesheets);
    }

    ReservationResponse reserveRooms(ReserveRoomCommand reserveRoomCommands) {
        if (reserveRoomCommands.getCustomerId() == null) {
            throw new IllegalArgumentException("Cannot make reservation due to unavailable customer");
        }

        final ReservationResponse reservationResponse = new ReservationResponse(hotelId, name);
        reservationResponse.setBookingPeriod(reservationResponse.getBookingPeriod());

        reserveRoomCommands.getDetails().forEach(reservationDetails ->  {
            final Timesheet timesheet = makeReservation(
                    reservationDetails.getRoomId(),
                    reservationDetails.getPlaces(),
                    reserveRoomCommands.getCustomerId(),
                    reserveRoomCommands.getBookingPeriod()
            );
            timesheets.add(timesheet);
            reservationResponse.addTimesheetId(timesheet.getTimesheetId());
            reservationResponse.sumTotal(timesheet.getTotalPrice());
        });

        return reservationResponse;
    }

    Hotel bookRooms(Set<TimesheetId> timesheetIds, CustomerId ownerId) {
        Map<TimesheetId, Timesheet> timesheetMap = new HashMap<>();
        timesheets.forEach(timesheet -> timesheetMap.put(timesheet.getTimesheetId(), timesheet));

        timesheetIds.forEach(id -> {
            Timesheet timesheet = timesheetMap.get(id);
            if (timesheet != null) {
                if (timesheet.getStatus() != RoomStatus.RESERVED) {
                    throw new IllegalArgumentException("Cannot Book not Reserved room");
                }
                if (!timesheet.getCustomerId().equals(ownerId)) {
                    throw new IllegalArgumentException("Only owner can book reserved rooms");
                }
                timesheet.setStatus(RoomStatus.BOOKED);
            }
        });
        return this;
    }

    Hotel releaseRooms(Set<TimesheetId> timesheetIds, CustomerId ownerId) {
        Map<TimesheetId, Timesheet> timesheetMap = new HashMap<>();
        timesheets.forEach(timesheet -> timesheetMap.put(timesheet.getTimesheetId(), timesheet));

        timesheetIds.forEach(id -> {
            Timesheet timesheet = timesheetMap.get(id);
            if (timesheet != null) {
                if (!timesheet.getCustomerId().equals(ownerId)) {
                    throw new IllegalArgumentException("Only owner can book reserved rooms");
                }
                timesheets.remove(timesheet);
            }
        });
        return this;
    }

    private Timesheet makeReservation(RoomId roomId, int spotsToReserve, CustomerId customerId, DateRange bookingPeriod) {
        if (customerId == null) {
            throw new IllegalArgumentException("Customer was not provided");
        }
        if (!isInAvailablePeriod(bookingPeriod)) {
            throw new IllegalArgumentException("Cannot make reservation due to unavailable period");
        }

        final Room room = findRoom(roomId);

        if (room.getAvailableSpots() < spotsToReserve) {
            throw new IllegalArgumentException("Cannot book more than: %s places".formatted(room.getAvailableSpots()));
        }

        return new Timesheet(
                new TimesheetId(UUID.randomUUID()),
                customerId,
                room.getTotalPrice(spotsToReserve),
                bookingPeriod,
                RoomStatus.RESERVED
        );
    }

    private Room findRoom(RoomId roomId) {
        return rooms.stream()
                .filter(room -> room.getSnapshot().getRoomId().equals(roomId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Cannot find room with id: " + roomId.getId().toString()));
    }

    private boolean isInAvailablePeriod(DateRange bookingPeriod) {
        return timesheets.stream().anyMatch(timesheet ->
                timesheet.getBookingPeriod().isInRange(bookingPeriod.getFrom()) ||
                timesheet.getBookingPeriod().isInRange(bookingPeriod.getTo())
        );
    }

    public HotelSnapshot getSnapshot() {
        return new HotelSnapshot(
                hotelId,
                name,
                rooms.stream().map(Room::getSnapshot).collect(Collectors.toSet()),
                timesheets.stream().map(Timesheet::getSnapshot).collect(Collectors.toSet())
        );
    }
}
