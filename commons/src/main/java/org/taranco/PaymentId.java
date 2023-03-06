package org.taranco;

import java.util.UUID;

public class PaymentId extends BaseId<UUID> {
    public PaymentId(UUID uuid) {
        super(uuid);
    }
}
