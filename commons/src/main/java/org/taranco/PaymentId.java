package org.taranco;

import java.util.Objects;
import java.util.UUID;

public class PaymentId implements Persistable<UUID> {
    private UUID id;

    public PaymentId(UUID uuid) {
        this.id = uuid;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public PaymentId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentId paymentId = (PaymentId) o;
        return Objects.equals(id, paymentId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
