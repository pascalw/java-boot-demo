package nl.kabisa.service.booking;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import nl.kabisa.service.rating.Rate;
import nl.kabisa.service.rating.RateProvider;
import nl.kabisa.util.Result;

public class BookingService {
    private final List<RateProvider> rateProviders;

    public BookingService(List<RateProvider> rateProviders) {
        this.rateProviders = rateProviders;
    }

    public BookingService(RateProvider... rateProviders) {
        this(Arrays.asList(rateProviders));
    }

    public Result<Booking, BookingException> book(Shipment shipment) {
        return bookShipment(shipment);
    }

    public Booking book2(Shipment shipment) throws BookingException {
        return bookShipment(shipment).unwrap();
    }

    private Result<Booking, BookingException> bookShipment(Shipment shipment) {
        return cheapestRate(shipment)
                .map(rate -> Result.<Booking, BookingException> ok(new Booking(rate)))
                .orElse(Result.err(new BookingException.NoRatesFoundException()));
    }

    private Optional<Rate> cheapestRate(Shipment shipment) {
        return rateProviders.stream()
                .flatMap(provider -> provider.getRates(shipment).stream())
                .min(Rate.cheapest());

    }
}
