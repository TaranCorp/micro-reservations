package org.taranco.booking.dto;

import org.taranco.BookingId;
import org.taranco.CustomerId;
import org.taranco.HotelId;
import org.taranco.vo.DateRange;
import org.taranco.vo.Money;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class BookingTracker {
    private UUID bookingId;
    private UUID customerId;
    private UUID hotelId;
    private String hotelName;
    private Set<RoomTracker> rooms = new HashSet<>();
    private DateRange bookingPeriod;
    private Instant bookingDate;
    private Instant paymentDate;
    private BigDecimal price;
    private String status;

    public BookingTracker(BookingId bookingId,
                          CustomerId customerId,
                          HotelId hotelId,
                          String hotelName,
                          Set<RoomTracker> rooms,
                          DateRange bookingPeriod,
                          Instant bookingDate,
                          Instant paymentDate,
                          Money price,
                          String status) {
        this.bookingId = bookingId == null ? null : bookingId.getId();
        this.customerId = customerId == null ? null : customerId.getId();
        this.hotelId = hotelId == null ? null : hotelId.getId();
        this.hotelName = hotelName;
        this.rooms = rooms;
        this.bookingPeriod = bookingPeriod;
        this.bookingDate = bookingDate;
        this.paymentDate = paymentDate;
        this.price = price == null ? null : price.getAmount();
        this.status = status;
    }

    public BookingTracker() {
    }

    public UUID getBookingId() {
        return bookingId;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public UUID getHotelId() {
        return hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public Set<RoomTracker> getRooms() {
        return rooms;
    }

    public DateRange getBookingPeriod() {
        return bookingPeriod;
    }

    public Instant getBookingDate() {
        return bookingDate;
    }

    public Instant getPaymentDate() {
        return paymentDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }
}
