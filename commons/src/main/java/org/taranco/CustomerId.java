package org.taranco;

import java.util.Objects;
import java.util.UUID;

public class CustomerId implements Persistable<UUID> {
    private UUID id;

    public CustomerId(UUID uuid) {
        this.id = uuid;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public CustomerId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerId that = (CustomerId) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
