package org.taranco;

import java.util.Objects;
import java.util.UUID;

public class BookingId implements Persistable<UUID> {
    private UUID id;

    public BookingId(UUID uuid) {
        this.id = uuid;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public BookingId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingId bookingId = (BookingId) o;
        return Objects.equals(id, bookingId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
