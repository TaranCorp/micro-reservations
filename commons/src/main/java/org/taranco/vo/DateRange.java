package org.taranco.vo;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class DateRange {
    public static DateRange createFromNowTo(Instant to) {
        return new DateRange(Instant.now(), to);
    }

    public static DateRange create(Instant from, Instant to) {
        return new DateRange(from, to);
    }

    private final Instant from;
    private final Instant to;

    private DateRange(Instant from, Instant to) {
        if (from == null) {
            throw new IllegalArgumentException("From date cannot be null");
        }
        if (to == null) {
            throw new IllegalArgumentException("To date cannot be null");
        }
        if (from.isAfter(to)) {
            throw new IllegalArgumentException("From date cannot be after To date");
        }
        this.from = from;
        this.to = to;
    }

    public Instant from() {
        return from;
    }

    public Instant to() {
        return to;
    }

    public long rangeBy(ChronoUnit unit) {
        return unit.between(from, to);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DateRange dateRange = (DateRange) o;
        return Objects.equals(from, dateRange.from) && Objects.equals(to, dateRange.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }
}
