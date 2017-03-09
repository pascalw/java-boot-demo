package nl.kabisa.service.booking;

import java.util.Objects;

import nl.kabisa.service.rating.Rate;

public class Booking {
    public final long id;
    public final Rate rate;

    public Booking(long id, Rate rate) {
        this.id = id;
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Booking booking = (Booking) o;
        return id == booking.id &&
               Objects.equals(rate, booking.rate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rate);
    }
}
