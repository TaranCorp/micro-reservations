package org.taranco;

import java.util.Objects;

public abstract class BaseId<ID> {
    private ID id;

    public BaseId(ID id) {
        if (id == null) {
            throw new IllegalArgumentException("Provided id cannot be null");
        }
        this.id = id;
    }

    public ID id() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseId<?> baseId = (BaseId<?>) o;
        return Objects.equals(id, baseId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
