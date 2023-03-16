package org.taranco.hotel.vo;

import org.taranco.CustomerId;
import org.taranco.RoomId;
import org.taranco.vo.DateRange;

import java.util.HashSet;
import java.util.Set;

public class ReserveRoomCommand {
    private CustomerId customerId;
    private DateRange bookingPeriod;
    private Set<RoomReservationDetails> details = new HashSet<>();

    public ReserveRoomCommand(CustomerId customerId, DateRange bookingPeriod, Set<RoomReservationDetails> details) {
        this.customerId = customerId;
        this.bookingPeriod = bookingPeriod;
        this.details.addAll(details);
    }

    public CustomerId getCustomerId() {
        return customerId;
    }

    public Set<RoomReservationDetails> getDetails() {
        return details;
    }

    public DateRange getBookingPeriod() {
        return bookingPeriod;
    }

    public static class RoomReservationDetails {
        private RoomId roomId;
        private int places;

        public RoomReservationDetails(RoomId roomId, int places) {
            this.roomId = roomId;
            this.places = places;
        }

        public RoomId getRoomId() {
            return roomId;
        }

        public int getPlaces() {
            return places;
        }
    }
}
