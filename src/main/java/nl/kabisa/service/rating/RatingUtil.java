package nl.kabisa.service.rating;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import nl.kabisa.service.booking.Shipment;

public final class RatingUtil {
    private RatingUtil() {
    }

    public static List<Rate> fakeRatesFor(Shipment shipment) {
        if (shipment.customerReference.equals("fail"))
            return Collections.emptyList();
        else
            return IntStream.range(0, 3).mapToObj(i -> Rate.random()).collect(Collectors.toList());
    }
}
