package org.taranco;

import java.util.Objects;
import java.util.UUID;

public class RoomId implements Persistable<UUID> {
    private UUID id;

    public RoomId(UUID uuid) {
        this.id = uuid;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public RoomId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomId roomId = (RoomId) o;
        return Objects.equals(id, roomId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
