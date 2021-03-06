package nl.kabisa.service.rating;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.google.common.base.MoreObjects;

import nl.kabisa.service.booking.Shipment;

public class Rate {
    public final long value;
    public final String provider;

    public static Rate random(String provider) {
        return new Rate(ThreadLocalRandom.current().nextLong(1, 100), provider);
    }

    public static List<Rate> randomRatesFor(Shipment shipment, String provider) {
        if (shipment.customerReference.equals("fail"))
            return Collections.emptyList();
        else
            return IntStream.range(0, 3).mapToObj(i -> Rate.random(provider)).collect(Collectors.toList());
    }

    public static Comparator<Rate> cheapest() {
        return (o1, o2) -> ((Long) o1.value).compareTo(o2.value);
    }

    public Rate(long value, String provider) {
        this.value = value;
        this.provider = provider;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Rate rate = (Rate) o;
        return value == rate.value &&
               Objects.equals(provider, rate.provider);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, provider);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("value", value)
                .toString();
    }
}
