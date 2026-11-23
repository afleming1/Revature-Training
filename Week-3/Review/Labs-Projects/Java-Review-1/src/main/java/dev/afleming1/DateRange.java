package dev.afleming1;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateRange {
    private final LocalDate start;
    private final LocalDate end;

    public DateRange(LocalDate start, LocalDate end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("Start and end dates are both required");
        }

        if (end.isBefore(start)) {
            throw new IllegalArgumentException("End date must be after start date");
        }

        this.start = start;
        this.end = end;
    }

    public boolean contains(LocalDate date) {
        return date != null && !date.isBefore(start) && !date.isAfter(end);
    }

    public long lengthInDays() {
        return ChronoUnit.DAYS.between(start, end) + 1;
    }

    public boolean overlaps(DateRange other) {
        return !start.isAfter(other.end) && !other.start.isAfter(end);
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }
}
