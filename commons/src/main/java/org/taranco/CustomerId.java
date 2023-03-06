package org.taranco;

import java.util.UUID;

public class CustomerId extends BaseId<UUID> {
    public CustomerId(UUID uuid) {
        super(uuid);
    }
}
