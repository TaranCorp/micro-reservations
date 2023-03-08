package org.taranco;

import java.util.UUID;

public class ReservationId extends BaseId<UUID> {
    public ReservationId(UUID uuid) {
        super(uuid);
    }

    public ReservationId() {
        super();
    }
}
