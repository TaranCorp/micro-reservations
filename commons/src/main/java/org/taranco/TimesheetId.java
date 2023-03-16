package org.taranco;

import java.util.Objects;
import java.util.UUID;

public class TimesheetId implements Persistable<UUID> {
    private UUID id;

    public TimesheetId(UUID uuid) {
        this.id = uuid;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public TimesheetId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimesheetId that = (TimesheetId) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
