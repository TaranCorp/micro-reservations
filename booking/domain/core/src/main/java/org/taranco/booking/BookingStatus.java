package org.taranco.booking;

import java.util.Set;

enum BookingStatus {
    PENDING, RESERVED, PAID, APPROVED, CANCELLING, CANCELLED;

    public static Set<BookingStatus> nextStates(BookingStatus previous) {
        return switch (previous) {
            case PENDING -> Set.of(RESERVED, CANCELLED);
            case RESERVED -> Set.of(PAID, CANCELLING);
            case PAID -> Set.of(APPROVED);
            case CANCELLING -> Set.of(CANCELLED);
            case APPROVED, CANCELLED -> Set.of();
        };
    }
}
