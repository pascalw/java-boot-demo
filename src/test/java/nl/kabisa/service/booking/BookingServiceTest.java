package nl.kabisa.service.booking;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.google.common.collect.ImmutableList;

import nl.kabisa.service.rating.Rate;
import nl.kabisa.service.rating.RateProvider;
import nl.kabisa.util.Result;

public class BookingServiceTest {
    private static final Rate CHEAPEST_RATE = new Rate(1, "cheap");

    private final RateProvider cheapProvider = shipment -> ImmutableList.of(CHEAPEST_RATE);
    private final RateProvider expensiveProvider = shipment -> ImmutableList.of(new Rate(100, "expensive"));

    private final BookingService bookingService = new BookingService(cheapProvider, expensiveProvider);
    private final Shipment shipment = new Shipment("ref", 100, 10, 20, "Amaze balls!");

    @Test
    public void books_with_cheapest_rate() {
        Result<Booking, BookingException> result = bookingService.book(shipment);

        assertTrue(result.isOk());
        assertEquals(CHEAPEST_RATE, result.result.rate);
    }

    @Test
    public void error_when_no_rates_available() {
        Result<Booking, BookingException> result = new BookingService().book(shipment);

        assertTrue(result.isErr());
        assertTrue(result.exception instanceof BookingException.NoRatesFoundException);
    }
}
