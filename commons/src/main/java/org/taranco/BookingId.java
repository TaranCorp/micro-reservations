package org.taranco;

import java.util.UUID;

public class BookingId extends BaseId<UUID> {
    public BookingId(UUID uuid) {
        super(uuid);
    }

    public BookingId() {
    }
}
