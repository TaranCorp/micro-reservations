package org.taranco;

import java.util.Objects;
import java.util.UUID;

public class HotelId implements Persistable<UUID> {
    private UUID id;

    public HotelId(UUID uuid) {
        this.id = uuid;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public HotelId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HotelId hotelId = (HotelId) o;
        return Objects.equals(id, hotelId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}