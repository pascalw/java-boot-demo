package nl.kabisa.service.rating;

import java.util.Comparator;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Rate {
    public final long value;

    public static Rate random() {
        return new Rate(ThreadLocalRandom.current().nextLong(1, 100));
    }

    public static Comparator<Rate> cheapest() {
        return (o1, o2) -> ((Long) o1.value).compareTo(o2.value);
    }

    public Rate(long value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Rate rate = (Rate) o;
        return value == rate.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
