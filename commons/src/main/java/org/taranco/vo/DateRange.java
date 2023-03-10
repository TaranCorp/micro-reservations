package org.taranco.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class DateRange {

    public static DateRange createFromNowTo(@JsonProperty("to") Instant to) {
        return new DateRange(Instant.now(), to);
    }

    @JsonCreator
    public static DateRange create(
            @JsonProperty("from") Instant from,
            @JsonProperty("to") Instant to)
    {
        return new DateRange(from, to);
    }

    private Instant from;
    private Instant to;

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

    protected DateRange() {
    }

    public Instant getFrom() {
        return from;
    }

    public Instant getTo() {
        return to;
    }

    public long getRangeBy(ChronoUnit unit) {
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
